package com.pampa.devs.openapi;

import com.pampa.devs.openapi.dto.CivilizationDTO;
import com.pampa.devs.openapi.dto.CivilizationListDTO;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@Service
public class CivilizationService {

  private List<CivilizationDTO> civilizationDTOList = new ArrayList<>();

  public CivilizationService() {
    initialize("civilizations.csv");
  }

  public Optional<CivilizationDTO> getById(int id) {
    return civilizationDTOList.stream()
        .filter(civilizationDTO -> civilizationDTO.getId().equals(id))
        .findFirst();
  }

  public CivilizationListDTO get() {
    return new CivilizationListDTO().civilizations(civilizationDTOList);
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
            CivilizationDTO civilizationDTO =
                new CivilizationDTO(
                    Integer.parseInt(values[0]),
                    values[1],
                    values[2],
                    values[3],
                    Arrays.asList(values[4]),
                    Arrays.asList(values[5]),
                    values[6],
                    Arrays.asList(values[7]));
            civilizationDTOList.add(civilizationDTO);
          }
        }
      }
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }
}
