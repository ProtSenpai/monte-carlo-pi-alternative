/*
 * Generated by: org.ow2.frascati.tinfi.opt.oo.InterceptorClassGenerator
 * on: Thu Jun 10 15:46:38 COT 2021
 */

package org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services;


public class ServicioCalcularPIInterceptorSCALCb56bb98SCACCIntent
extends org.ow2.frascati.tinfi.TinfiComponentInterceptor<org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioCalcularPI>
implements org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioCalcularPI,org.objectweb.fractal.julia.Interceptor {

  private juliac.generated.SCALifeCycleControllerImpl _lc;
  private static java.lang.reflect.Method[] METHODS;
  public ServicioCalcularPIInterceptorSCALCb56bb98SCACCIntent()  {
  }

  private ServicioCalcularPIInterceptorSCALCb56bb98SCACCIntent(Object obj)  {
    setFcItfDelegate(obj);
  }

  public void initFcController(org.objectweb.fractal.julia.InitializationContext ic) throws org.objectweb.fractal.api.factory.InstantiationException  {
    super.initFcController(ic);
    Object olc = ic.getInterface("lifecycle-controller");
    if ( !( olc instanceof juliac.generated.SCALifeCycleControllerImpl ) )
    {
      while ( olc instanceof org.objectweb.fractal.julia.Interceptor )
      {
        olc = ((org.objectweb.fractal.julia.Interceptor)olc).getFcItfDelegate();
      }

    }
    _lc = (juliac.generated.SCALifeCycleControllerImpl) olc;
    initIntentHandlersMap(METHODS);
  }

  public Object clone()  {
    ServicioCalcularPIInterceptorSCALCb56bb98SCACCIntent clone = new ServicioCalcularPIInterceptorSCALCb56bb98SCACCIntent(getFcItfDelegate());
    initFcClone(clone);
    clone._lc = _lc;
    clone.initIntentHandlersMap(METHODS);
    return clone;
  }

  public double recibirPuntos(final long arg0,final int arg1)  {
    synchronized(_lc)
    {
      if ( _lc.fcState != 2 )
      {
        _lc.incrementFcInvocationCounter();
      }
      else
      {
        _lc.fcInvocationCounter++;
      }

    }

      try {
    org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioCalcularPI impl = pushFcAndGet("servicioCalcularPI",org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioCalcularPI.class,this);
    try {
    java.util.List<org.ow2.frascati.tinfi.api.IntentHandler> handlers = intentHandlersMap.get(METHODS[0]);
    try {
      if( handlers.size() == 0 ) {
        double ret = impl.recibirPuntos(arg0,arg1);
        return ret;
      }
      else {
        org.objectweb.fractal.api.Component comp = getFcComponent();
        org.objectweb.fractal.api.Interface itf = getFcItf();
        org.ow2.frascati.tinfi.IntentJoinPointImpl<org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioCalcularPI> ijp = new org.ow2.frascati.tinfi.IntentJoinPointImpl();
        ijp.init(handlers,comp,itf,impl,METHODS[0],(Object)arg0,(Object)arg1);
    Object proceed = ijp.proceed();
    double ret = (proceed==null) ? 0.0 : (java.lang.Double)proceed;
    return ret;
      }
    }
    catch( Throwable t ) {
      if( t instanceof RuntimeException ) {
        throw (RuntimeException) t;
      }
      throw new org.ow2.frascati.tinfi.TinfiRuntimeException(t);
    }
    }
    finally {
      releaseFcAndPop(impl,false);
    }
      }
      finally {
    synchronized(_lc)
    {
      if ( _lc.fcState != 2 )
      {
        _lc.decrementFcInvocationCounter();
      }
      else
      {
        _lc.fcInvocationCounter--;
      }

    }

      }
  }

  static  {
    try {
      METHODS = new java.lang.reflect.Method[]{
        org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioCalcularPI.class.getMethod("recibirPuntos",long.class,int.class),
      };
    }
    catch( NoSuchMethodException e ) {
      throw new org.ow2.frascati.tinfi.TinfiRuntimeException(e);
    }
  }

}
