package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.otus.model.Measurement;
import ru.otus.model.MeasurementDto;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ResourcesFileLoader implements Loader {
    private final String fileName;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        var sourceFile = getClass().getClassLoader().getResourceAsStream(fileName);
        try {
            MappingIterator<MeasurementDto> iterator = objectMapper.readerFor(MeasurementDto.class)
                    .readValues(sourceFile);
            return iterator.readAll().stream()
                    .map(dto -> new Measurement(dto.getName(), dto.getValue()))
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            throw new FileProcessException(exception);
        }
    }
}
