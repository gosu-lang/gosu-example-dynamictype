classpath "../dynamictype.jar, ."

uses dynamic.Dynamic

foo( "hello" )

function foo( value: Dynamic )
{
  print( value.charAt( 1 ) )
}
