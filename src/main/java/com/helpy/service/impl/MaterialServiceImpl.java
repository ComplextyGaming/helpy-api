package com.helpy.service.impl;

import com.helpy.dto.MaterialesResumenDTO;
import com.helpy.model.Material;
import com.helpy.repository.GenericRepository;
import com.helpy.repository.MaterialRepository;
import com.helpy.service.MaterialService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.*;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;


@Service("MaterialServiceImpl")
@Slf4j
public class MaterialServiceImpl extends CrudServiceImpl<Material, Long> implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    protected GenericRepository<Material, Long> getRepository() {
        return materialRepository;
    }

    @Override

    public List<Material> getByExpertId(Long id) throws Exception {
        return materialRepository.findMaterialsByExpert_Id(id);
    }

    public byte[] generarReporte() {
        byte[] data = null;

        Map<String, Object> params = new HashMap<>();
        params.put("txt_titulo", "Prueba de titulo");

        try {
            File file = new ClassPathResource("/reports/materiales.jasper").getFile();
            JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, new JRBeanCollectionDataSource(this.listarResumen()));
            data = JasperExportManager.exportReportToPdf(print);
        } catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<MaterialesResumenDTO> listarResumen() {
        List<MaterialesResumenDTO> materialesResumenDTOS = new ArrayList<>();
        var materials = materialRepository.findAll();

        materials.stream()
                .collect(Collectors.groupingBy(mat -> mat.getGame().getName(), Collectors.counting()))
                .forEach((name, count)->
                    materialesResumenDTOS.add( new MaterialesResumenDTO(name, count.intValue())));
        return materialesResumenDTOS;

    }

    @Override
    public List<Material> getByExpertIdAndTagIg(Long expertId, Long tagId) {
        var materials= materialRepository.findMaterialsByExpert_Id(expertId);
        var materialesTag = new ArrayList<Material>();
        materials.forEach(material -> {
            material.getTags().forEach(tag -> {
                if (Objects.equals(tag.getId(), tagId)){
                    materialesTag.add(material);
                }
            });
        });
        return materialesTag;
    }
}
