package custom.dyntype;

import gw.lang.reflect.IAnnotationInfo;
import gw.lang.reflect.IMethodCallHandler;
import gw.lang.reflect.IMethodInfo;
import gw.lang.reflect.IParameterInfo;
import gw.lang.reflect.IType;
import gw.lang.reflect.ITypeInfo;
import gw.lang.reflect.MethodInfoBase;
import gw.lang.reflect.TypeSystem;
import gw.lang.reflect.java.IJavaType;
import gw.lang.reflect.ParameterInfoBase;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 */
public class DynamicMethodInfo extends MethodInfoBase implements IMethodCallHandler
{
  private String _strName;
  private IType[] _paramTypes;
  private IParameterInfo[] _paramInfos;


  protected DynamicMethodInfo( ITypeInfo container, String strName, IType... paramTypes )
  {
    super( container );
    _strName = strName;
    _paramTypes = paramTypes == null ? IType.EMPTY_ARRAY : paramTypes;
    makeParameters();
  }

  private void makeParameters()
  {
    _paramInfos = new IParameterInfo[_paramTypes.length];
    for( int i = 0; i < _paramTypes.length; i++ )
    {
      _paramInfos[i] = new ParameterInfoBase( this, _strName, _paramTypes[i] );
    }
  }

  @Override
  public IParameterInfo[] getParameters()
  {
    return _paramInfos;
  }

  @Override
  public IType getReturnType()
  {
    return IJavaType.OBJECT;
  }

  @Override
  public IMethodCallHandler getCallHandler()
  {
    return this;
  }

  @Override
  public String getName()
  {
    return _strName;
  }

  @Override
  public Map<IType, List<IAnnotationInfo>> getDeclaredAnnotations()
  {
    return Collections.emptyMap();
  }

  @Override
  public Object handleCall( Object ctx, Object... args )
  {
    IType type = TypeSystem.getFromObject( ctx );
    IMethodInfo mi = type.getTypeInfo().getCallableMethod( getName(), _paramTypes );
    return mi.getCallHandler().handleCall( ctx, args );
  }
}
