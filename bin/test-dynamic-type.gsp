classpath ".."
//typeloader custom.dyntype.DynamicTypeLoader

uses dynamic.Dynamic

foo( "hello" )

function foo( value: Dynamic )
{
  print( value.charAt( 1 ) )
}
