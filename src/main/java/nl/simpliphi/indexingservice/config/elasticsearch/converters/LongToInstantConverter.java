package nl.simpliphi.indexingservice.config.elasticsearch.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.Instant;

@ReadingConverter
public class LongToInstantConverter implements Converter<Long, Instant> {

  @Override
  public Instant convert(Long aLong) {
    return Instant.ofEpochMilli(aLong);
  }
}
