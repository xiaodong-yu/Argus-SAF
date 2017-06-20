grammar Safsu;


summaryFile : summary* EOF;

summary
  : signature ':' suRule* ';'
  ;

signature
  : UID
  ;

suRule
  : clearRule
  | binaryRule
  ;

clearRule
  : '~' (suThis | arg | global)    // clear heap of given element
  ;

binaryRule
  : lhs ops rhs                    // rhs ops lhs
  ;

ops
  : '='
  | '+='
  | '-='
  ;

lhs
  : suThis
  | arg
  | global
  | ret
  ;

rhs
  : suThis
  | arg
  | global
  | instance
  ;

suThis
  : 'this' heap?                   // this or this.f1
  ;

arg
  : 'arg' ':' Digits heap?         // arg:1 or arg:1.f1 or arg1[] or arg1.f1[][]
  ;

global
  : UID heap?                      // `com.my.Class.GlobalVariable`
  ;

heap
  : heapAccess+
  ;

heapAccess
  : fieldAccess
  | arrayAccess
  | mapAccess
  ;

fieldAccess
  : '.' ID
  ;

arrayAccess
  : '[]'
  ;

mapAccess
  : '(' rhs? ')'          // "()" means all values
  ;

instance
  : type '@' location      // com.my.Object[]@L123
  ;

type
  : javaType
  | stringLit
  ;

javaType
  : ID ('.' ID)* unknown? arrayAccess*
  ;

unknown
  : '?'
  ;

stringLit
  : STRING
  | MSTRING
  ;

ret
  : 'ret' heap?
  ;

location
  : virtualLocation
  | concreteLocation
  ;

virtualLocation
  : '~'
  ;

concreteLocation
  : ID
  ;

UID: '`' ( ~( '\n' | '\r' | '\t' | '\u000C' | '`' ) )* '`';

ID: LETTER ( LETTER | DIGIT )*;

Digits : DIGIT+ ;

STRING
  :  '"' ( EscapeSequence | ~('\\'|'"') )* '"'
  ;

MSTRING
  : '"""' .*? '"""'
  ;

fragment
EscapeSequence
  : '\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')
  ;

fragment
DIGIT : '0'..'9' ;

fragment
LETTER
  : '\u0041'..'\u005a'       // A-Z
  | '\u005f'                 // _
  | '\u0061'..'\u007a'       // a-z
  ;

WS
  : [ \r\t\u000C\n]+ -> channel(HIDDEN)
  ;

COMMENT
  : '/*' .*? '*/'    -> channel(2)
  ;

LINE_COMMENT
  : '//' ~[\r\n]*    -> channel(2)
  ;