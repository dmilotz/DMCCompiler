/* Generated By:JJTree: Do not edit this line. ASTMultiply.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=wci.intermediate.icodeimpl.ICodeNodeImpl,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package wci.frontend;

public
class ASTMultiply extends SimpleNode {
  public ASTMultiply(int id) {
    super(id);
  }

  public ASTMultiply(Language p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(LanguageVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=9eae32772cf637400833f905dd715240 (do not edit this line) */
