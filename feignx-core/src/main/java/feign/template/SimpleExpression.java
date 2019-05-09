package feign.template;

public class SimpleExpression extends Expression {

  SimpleExpression(String variableSpecification) {
    super(variableSpecification);
  }

  SimpleExpression(String variableSpecification, boolean includeName) {
    super(variableSpecification, includeName);
  }

  @Override
  protected boolean isCharacterAllowed(char character) {
    /* only unreserved and our delimiter are allowed */
    return UriUtils.isUnreserved(character);
  }

  @Override
  protected String getDelimiter() {
    return Expression.DEFAULT_DELIMITER;
  }

  @Override
  protected String getPrefix() {
    return "";
  }
}