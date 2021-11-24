package com.helpy.service.impl;

import com.helpy.dto.MaterialesResumenDTO;
import com.helpy.model.Material;
import com.helpy.repository.GenericRepository;
import com.helpy.repository.MaterialRepository;
import com.helpy.service.MaterialService;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("MaterialServiceImpl")
public class MaterialServiceImpl extends CrudServiceImpl<Material, Long> implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    protected GenericRepository<Material, Long> getRepository() {
        return materialRepository;
    }

    @Override
    public byte[] generarReporte() {
        byte[] data = null;

        Map<String, Object> params = new HashMap<>();
        params.put("txt_titulo", "Prueba de titulo");

        try {
            File file = new ClassPathResource("/reports/consultas.jasper").getFile();
            JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, new JRBeanCollectionDataSource(this.listarResumen()));
        } catch (Exception e){

        }
        return data;
    }

    @Override
    public List<MaterialesResumenDTO> listarResumen() {
        List<MaterialesResumenDTO> materiales = new ArrayList<>();
        var materials = materialRepository.findAll();
        materials.stream()
                .collect(Collectors.groupingBy(mat -> mat.getGame().getName(), Collectors.counting()))
                .forEach((name, count)->System.out.println(name+"\t"+count));

        return null;
    }
}
