package custom.dyntype;

import gw.lang.reflect.BaseTypeInfo;
import gw.lang.reflect.IMethodInfo;
import gw.lang.reflect.IPropertyInfo;
import gw.lang.reflect.IType;
import gw.lang.reflect.TypeSystem;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class DynamicTypeInfo extends BaseTypeInfo
{
  private Map<CharSequence, IPropertyInfo> _piCache;


  public DynamicTypeInfo( IType type )
  {
    super( type );
    _piCache = new HashMap<CharSequence, IPropertyInfo>();
  }

  @Override
  public IPropertyInfo getProperty( CharSequence propName )
  {
    IPropertyInfo pi = _piCache.get( propName );
    if( pi == null )
    {
      TypeSystem.lock();
      try
      {
        pi = _piCache.get( propName );
        if( pi == null )
        {
          pi = new DynamicPropertyInfo( this, propName.toString() );
          _piCache.put( propName, pi );
        }
      }
      finally
      {
        TypeSystem.unlock();
      }
    }
    return pi;
  }

  @Override
  public IMethodInfo getMethod( CharSequence methodName, IType... params )
  {
    // Probably should cache these, but doing so could be more expensive e.g., the lookup involves param types
    return new DynamicMethodInfo( this, methodName.toString(), params );
  }

  @Override
  public IMethodInfo getCallableMethod( CharSequence method, IType... params )
  {
    return new DynamicMethodInfo( this, method.toString(), params );
  }
}
