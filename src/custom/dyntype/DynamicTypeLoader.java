package custom.dyntype;

import gw.lang.reflect.IType;
import gw.lang.reflect.TypeLoaderBase;
import gw.lang.reflect.TypeSystem;
import gw.util.concurrent.LazyVar;

import java.util.Collections;
import java.util.List;
import java.util.Set;                              

/**
 */
public class DynamicTypeLoader extends TypeLoaderBase
{
  private LazyVar<DynamicType> _type =
    new LazyVar<DynamicType>( TypeSystem.getGlobalLock() )
    {
      protected DynamicType init()
      {
        return new DynamicType( DynamicTypeLoader.this );
      }
    };


  @Override
  public IType getType( String fullyQualifiedName )
  {
    if( fullyQualifiedName != null &&
        fullyQualifiedName.equals( DynamicType.QNAME ) )
    {
      return _type.get();
    }
    return null;
  }

  @Override
  public Set<? extends CharSequence> getAllTypeNames()
  {
    return Collections.singleton( DynamicType.QNAME );
  }

  @Override
  public Set<String> getAllNamespaces()
  {
    return Collections.singleton( DynamicType.PKG );
  }

  @Override
  public List<String> getHandledPrefixes()
  {
    return Collections.singletonList( DynamicType.PKG );
  }
}
