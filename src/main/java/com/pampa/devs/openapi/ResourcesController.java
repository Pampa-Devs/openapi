package com.pampa.devs.openapi;

import com.pampa.devs.openapi.api.ResourcesApi;
import com.pampa.devs.openapi.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController(value = "v1")
public class ResourcesController implements ResourcesApi {

  @Autowired private CivilizationService civilizationService;

  @Autowired private StructuresService structuresService;

  @Override
  public ResponseEntity<CivilizationListDTO> civilizationsGet() {
    return new ResponseEntity<>(civilizationService.get(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CivilizationDTO> findCivilizationById(String id) {
    Optional<CivilizationDTO> civilizationDTO = civilizationService.getById(Integer.parseInt(id));

    if (civilizationDTO.isPresent()) {
      return new ResponseEntity<>(civilizationDTO.get(), HttpStatus.OK);
    }

    return new ResponseEntity(HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<StructureListDTO> structuresGet() {
    return new ResponseEntity<>(structuresService.get(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<StructureDTO> findStructureById(String id) {
    Optional<StructureDTO> structureDTO = structuresService.getById(Integer.parseInt(id));

    if (structureDTO.isPresent()) {
      return new ResponseEntity<>(structureDTO.get(), HttpStatus.OK);
    }

    return new ResponseEntity(HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<TechnologyDTO> findTechnologyById(String id) {
    return null;
  }

  @Override
  public ResponseEntity<UnitDTO> findUnitById(String id) {
    return null;
  }

  @Override
  public ResponseEntity<TechnologyListDTO> technologiesGet() {
    return null;
  }

  @Override
  public ResponseEntity<UnitListDTO> unitsGet() {
    return null;
  }
}
