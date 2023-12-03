grammar lang;

program: statement*;

statement: assignment_statement ';';

assignment_statement: IDENTIFIER? IDENTIFIER '=' addition_expression;

addition_expression: multiplication_expression (('+' | '-') multiplication_expression)*;

multiplication_expression: exponentiation_expression (('*' | '/' | '%') exponentiation_expression)*;

exponentiation_expression: number ('^' number)*;

number: INTEGER | float;
float: (INTEGER '.' INTEGER?) | (INTEGER? '.' INTEGER);

IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]*;
INTEGER: [0-9];
WHITESPACE: [ \n\t\r] -> skip;