/* Generated By:JJTree: Do not edit this line. ASTFuncReturnType.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=wci.intermediate.icodeimpl.ICodeNodeImpl,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package wci.frontend;

public
class ASTFuncReturnType extends SimpleNode {
  public ASTFuncReturnType(int id) {
    super(id);
  }

  public ASTFuncReturnType(Language p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(LanguageVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=2954b0804e64b41d8be1fff478fe9b52 (do not edit this line) */