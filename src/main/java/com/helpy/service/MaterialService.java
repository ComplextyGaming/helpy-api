package com.helpy.service;

import com.helpy.dto.MaterialesResumenDTO;
import com.helpy.model.Material;

import java.util.List;

public interface MaterialService extends CrudService<Material, Long> {
    byte[] generarReporte();
    List<MaterialesResumenDTO> listarResumen();
}
