package nl.simpliphi.indexingservice.config.elasticsearch.converters;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class StringConverter implements Converter<String, String> {

  @Override
  public String convert(String s) {
    if (StringUtils.isBlank(s)) {
      return null;
    }
    return s.trim();
  }
}
