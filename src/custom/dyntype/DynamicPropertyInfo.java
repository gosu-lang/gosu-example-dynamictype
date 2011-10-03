package custom.dyntype;

import gw.lang.reflect.IAnnotationInfo;
import gw.lang.reflect.IPropertyAccessor;
import gw.lang.reflect.IPropertyInfo;
import gw.lang.reflect.IType;
import gw.lang.reflect.ITypeInfo;
import gw.lang.reflect.PropertyInfoBase;
import gw.lang.reflect.TypeSystem;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 */
public class DynamicPropertyInfo extends PropertyInfoBase implements IPropertyAccessor
{
  private String _strName;


  DynamicPropertyInfo( ITypeInfo container, String strName )
  {
    super( container );
    _strName = strName;
  }

  @Override
  public boolean isReadable()
  {
    return true;
  }

  @Override
  public boolean isWritable( IType whosAskin )
  {
    return true;
  }

  @Override
  public IPropertyAccessor getAccessor()
  {
    return this;
  }

  @Override
  public IType getFeatureType()
  {
    return getOwnersType();
  }

  @Override
  public Map<IType, List<IAnnotationInfo>> getDeclaredAnnotations()
  {
    return Collections.emptyMap();
  }

  @Override
  public boolean hasAnnotation( IType type )
  {
    return false;
  }

  @Override
  public String getName()
  {
    return _strName;
  }

  @Override
  public Object getValue( Object ctx )
  {
    IType type = TypeSystem.getFromObject( ctx );
    IPropertyInfo pi = type.getTypeInfo().getProperty( getName() );
    return pi.getAccessor().getValue( ctx );
  }

  @Override
  public void setValue( Object ctx, Object value )
  {
    IType type = TypeSystem.getFromObject( ctx );
    IPropertyInfo pi = type.getTypeInfo().getProperty( getName() );
    pi.getAccessor().setValue( ctx, value );
  }
}
