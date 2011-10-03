package custom.dyntype;

import gw.lang.reflect.IPlaceholder;
import gw.lang.reflect.IType;
import gw.lang.reflect.ITypeInfo;
import gw.lang.reflect.ITypeLoader;
import gw.lang.reflect.TypeBase;
import gw.util.concurrent.LazyVar;

import java.util.Collections;
import java.util.List;

/**
 */
public class DynamicType extends TypeBase implements IPlaceholder
{
  public static final String PKG = "dynamic";
  public static final String RNAME = "Dynamic";
  public static final String QNAME = PKG +'.'+ RNAME;

  private DynamicTypeLoader _typeLoader;
  private LazyVar<DynamicTypeInfo> _typeInfo;


  public DynamicType( DynamicTypeLoader typeLoader )
  {
    _typeLoader = typeLoader;
    _typeInfo =
      new LazyVar<DynamicTypeInfo>()
      {
        @Override
        protected DynamicTypeInfo init()
        {
          return new DynamicTypeInfo( DynamicType.this );
        }
      };
  }

  @Override
  public String getName()
  {
    return QNAME;
  }

  @Override
  public String getRelativeName()
  {
    return RNAME;
  }

  @Override
  public String getNamespace()
  {
    return PKG;
  }

  @Override
  public ITypeLoader getTypeLoader()
  {
    return _typeLoader;
  }

  @Override
  public IType getSupertype()
  {
    return null;
  }

  @Override
  public List<? extends IType> getInterfaces()
  {
    return Collections.emptyList();
  }

  @Override
  public ITypeInfo getTypeInfo()
  {
    return _typeInfo.get();
  }

  @Override
  public boolean isPlaceholder()
  {
    return true;
  }

  @Override
  public boolean isAssignableFrom( IType type )
  {
    return !type.isPrimitive();
  }
}
