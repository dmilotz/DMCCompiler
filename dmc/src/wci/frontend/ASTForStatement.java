/* Generated By:JJTree: Do not edit this line. ASTForStatement.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=wci.intermediate.icodeimpl.ICodeNodeImpl,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package wci.frontend;

public
class ASTForStatement extends SimpleNode {
  public ASTForStatement(int id) {
    super(id);
  }

  public ASTForStatement(Language p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(LanguageVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=8d9c2b8576eb832a47ae7ee37b572a67 (do not edit this line) */