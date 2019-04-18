package io.vertx.ext.web.validation.impl;

import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.validation.MalformedValueException;
import io.vertx.ext.web.validation.ValueParser;

import java.util.List;

public class SplitterCharArrayItemByItemParser implements ValueParser<String> {

  private final ValueParser<String>[] itemsParser;
  private final ValueParser<String> additionalItemsParser;
  private final String separator;

  @SuppressWarnings("unchecked")
  public SplitterCharArrayItemByItemParser(List<ValueParser<String>> itemsParser, ValueParser<String> additionalItemsParser, String separator) {
    this.itemsParser = itemsParser.toArray(new ValueParser[0]);
    this.additionalItemsParser = additionalItemsParser;
    this.separator = separator;
  }

  @Override
  public JsonArray parse(String serialized) throws MalformedValueException {
    JsonArray result = new JsonArray();
    String[] splitted = serialized.split(separator, -1);
    for (int i = 0; i < splitted.length; i++) {
      if (i < itemsParser.length)
        result.add(parseValue(splitted[i], itemsParser[i]));
      else
        result.add(parseValue(splitted[i], additionalItemsParser));
    }
    return result;
  }

  private Object parseValue(String v, ValueParser<String> parser) {
    return v.isEmpty() ? null : parser.parse(v);
  }

}