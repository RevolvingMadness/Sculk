grammar lang;

program: statement*;

statement: assignment_statement ';';

assignment_statement: IDENTIFIER (((binary_operator? '=' addition_expression) | (IDENTIFIER '=' addition_expression)) | ('++' | '--'));

addition_expression: multiplication_expression (('+' | '-') multiplication_expression)*;

multiplication_expression: exponentiation_expression (('*' | '/' | '%') exponentiation_expression)*;

exponentiation_expression: primary_expression ('^' primary_expression)*;

primary_expression: '-'? (INTEGER | float | IDENTIFIER | '(' addition_expression ')' | boolean | string | resource);

binary_operator: '+' | '-' | '*' | '/' | '%' | '^';

float: (INTEGER '.' INTEGER?) | (INTEGER? '.' INTEGER);
boolean: 'true' | 'false';
string: '"' .*? '"';
resource: IDENTIFIER ':' IDENTIFIER;

IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]*;
INTEGER: [0-9];
WHITESPACE: [ \n\t\r] -> skip;
COMMENTS: '//' .*?'\n' -> skip;