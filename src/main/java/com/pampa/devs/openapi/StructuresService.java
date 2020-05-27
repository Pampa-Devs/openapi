package com.pampa.devs.openapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pampa.devs.openapi.dto.CivilizationDTO;
import com.pampa.devs.openapi.dto.CostDTO;
import com.pampa.devs.openapi.dto.StructureDTO;
import com.pampa.devs.openapi.dto.StructureListDTO;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StructuresService {
  private List<StructureDTO> structureDTOS = new ArrayList<>();

  public StructuresService() {
    initialize("structures.csv");
  }

  public Optional<StructureDTO> getById(int id) {
    return structureDTOS.stream()
        .filter(structureDTO -> structureDTO.getId().equals(id))
        .findFirst();
  }

  public StructureListDTO get() {
    return new StructureListDTO().structures(structureDTOS);
  }

  private void initialize(String fileName) {
    try {
      ClassLoader classLoader = getClass().getClassLoader();

      URL resource = classLoader.getResource(fileName);
      if (resource == null) {
        throw new IllegalArgumentException("file is not found!");
      } else {
        try (FileReader reader = new FileReader(resource.getFile());
            BufferedReader br = new BufferedReader(reader)) {
          String line;
          while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            if (values[0].equalsIgnoreCase("id")) continue;

            CostDTO cost = new ObjectMapper().readValue(values[4], CostDTO.class);

            try {
              StructureDTO structureDTO =
                  new StructureDTO(
                      Integer.parseInt(values[0]),
                      values[1],
                      "",
                      values[2],
                      values[3],
                      cost,
                      values[5].trim(),
                      values[6].trim(),
                      values[7].trim(),
                      values[8],
                      values[9],
                      values[10].trim(),
                      values[11].trim(),
                      Arrays.asList(values[12].trim()));
              structureDTOS.add(structureDTO);
            } catch (Exception ex) {
              System.out.println(ex.getMessage());
            }
          }
        }
      }
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }
}
