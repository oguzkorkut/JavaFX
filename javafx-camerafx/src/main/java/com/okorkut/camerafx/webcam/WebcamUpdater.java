package com.okorkut.camerafx.webcam;

import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.okorkut.camerafx.webcam.ds.cgt.WebcamGetImageTask;

public class WebcamUpdater
  implements Runnable
{
  public static abstract interface DelayCalculator
  {
    public abstract long calculateDelay(long paramLong, double paramDouble);
  }
  
  public static class DefaultDelayCalculator
    implements WebcamUpdater.DelayCalculator
  {
    public long calculateDelay(long snapshotDuration, double deviceFps)
    {
      long delay = Math.max(20L - snapshotDuration, 0L);
      return delay;
    }
  }
  
  private static final class UpdaterThreadFactory implements ThreadFactory {

		private static final AtomicInteger number = new AtomicInteger(0);

		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r, String.format("webcam-updater-thread-%d", number.incrementAndGet()));
			t.setUncaughtExceptionHandler(WebcamExceptionHandler.getInstance());
			t.setDaemon(true);
			return t;
		}

	}
  
  private static final Logger LOG = LoggerFactory.getLogger(WebcamUpdater.class);
  private static final int TARGET_FPS = 50;
  private static final UpdaterThreadFactory THREAD_FACTORY = new UpdaterThreadFactory();
  private ScheduledExecutorService executor = null;
  private final AtomicReference<BufferedImage> image = new AtomicReference();
  private Webcam webcam = null;
  private volatile double fps = 0.0D;
  private AtomicBoolean running = new AtomicBoolean(false);
  private volatile boolean imageNew = false;
  private final DelayCalculator delayCalculator;
  
  protected WebcamUpdater(Webcam webcam)
  {
    this(webcam, new DefaultDelayCalculator());
  }
  
  public WebcamUpdater(Webcam webcam, DelayCalculator delayCalculator)
  {
    this.webcam = webcam;
    if (delayCalculator == null) {
      this.delayCalculator = new DefaultDelayCalculator();
    } else {
      this.delayCalculator = delayCalculator;
    }
  }
  
  public void start()
  {
    if (this.running.compareAndSet(false, true))
    {
      this.image.set(new WebcamGetImageTask(Webcam.getDriver(), this.webcam.getDevice()).getImage());
      
      this.executor = Executors.newSingleThreadScheduledExecutor(THREAD_FACTORY);
      this.executor.execute(this);
      
      LOG.debug("Webcam updater has been started");
    }
    else
    {
      LOG.debug("Webcam updater is already started");
    }
  }
  
  public void stop()
  {
    if (this.running.compareAndSet(true, false))
    {
      this.executor.shutdown();
      while (!this.executor.isTerminated()) {
        try
        {
          this.executor.awaitTermination(100L, TimeUnit.MILLISECONDS);
        }
        catch (InterruptedException e)
        {
          return;
        }
      }
      LOG.debug("Webcam updater has been stopped");
    }
    else
    {
      LOG.debug("Webcam updater is already stopped");
    }
  }
  
  public void run()
  {
    if (!this.running.get()) {
      return;
    }
    try
    {
      tick();
    }
    catch (Throwable t)
    {
      WebcamExceptionHandler.handle(t);
    }
  }
  
  private void tick()
  {
    if (!this.webcam.isOpen()) {
      return;
    }
    WebcamDriver driver = Webcam.getDriver();
    WebcamDevice device = this.webcam.getDevice();
    
    assert (driver != null);
    assert (device != null);
    
    boolean imageOk = false;
    long t1 = System.currentTimeMillis();
    try
    {
      this.image.set(this.webcam.transform(new WebcamGetImageTask(driver, device).getImage()));
      this.imageNew = true;
      imageOk = true;
    }
    catch (WebcamException e)
    {
      WebcamExceptionHandler.handle(e);
    }
    long t2 = System.currentTimeMillis();
    
    double deviceFps = -1.0D;
    if ((device instanceof WebcamDevice.FPSSource)) {
      deviceFps = ((WebcamDevice.FPSSource)device).getFPS();
    }
    long duration = t2 - t1;
    long delay = this.delayCalculator.calculateDelay(duration, deviceFps);
    
    long delta = duration + 1L;
    if (deviceFps >= 0.0D) {
      this.fps = deviceFps;
    } else {
      this.fps = ((4.0D * this.fps + 1000L / delta) / 5.0D);
    }
    if (this.webcam.isOpen()) {
      try
      {
        this.executor.schedule(this, delay, TimeUnit.MILLISECONDS);
      }
      catch (RejectedExecutionException e)
      {
        LOG.trace("Webcam update has been rejected", e);
      }
    }
    if (imageOk) {
      this.webcam.notifyWebcamImageAcquired((BufferedImage)this.image.get());
    }
  }
  
  public BufferedImage getImage()
  {
    int i = 0;
    while (this.image.get() == null)
    {
      try
      {
        Thread.sleep(100L);
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
      if (i++ > 100)
      {
        LOG.error("Image has not been found for more than 10 seconds");
        return null;
      }
    }
    this.imageNew = false;
    
    return (BufferedImage)this.image.get();
  }
  
  protected boolean isImageNew()
  {
    return this.imageNew;
  }
  
  public double getFPS()
  {
    return this.fps;
  }
}
