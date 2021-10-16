package com.helpy.controller;

import com.helpy.dto.SocialRequest;
import com.helpy.dto.SocialResponse;
import com.helpy.exception.ResourceNotFoundException;
import com.helpy.model.Expert;
import com.helpy.model.Social;
import com.helpy.service.ExpertService;
import com.helpy.service.SocialService;
import com.helpy.util.SocialConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/social")
public class SocialController {

    @Autowired
    private SocialConverter converter;

    @Autowired
    private SocialService socialService;

    @Autowired
    private ExpertService expertService;

    @GetMapping
    public ResponseEntity<List<SocialResponse>> getAllSocials() throws Exception{
        var socials = socialService.getAll();
        return new ResponseEntity<>(converter.convertSocialToResponse(socials), HttpStatus.OK);
    }

    @PostMapping("/expert/{id}")
    public ResponseEntity<SocialResponse> createSocial(@Valid @RequestBody SocialRequest request,
                                                       @Valid @PathVariable(name = "id") Long id) throws Exception {
        Social social;

        if(request.getUrl() == null || request.getUrl() == ""){
            throw new ResourceNotFoundException("url is empty");
        }

        Expert exist = expertService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Expert not found"));

        var tem = converter.convertSocialToEntity(request);
        tem.setExpert(exist);
        social = socialService.create(tem);
        return new ResponseEntity<>(converter.convertSocialToResponse(social), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocialResponse> getById(@Valid @PathVariable(name = "id") Long id) throws Exception {
        var social = socialService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Social not found"));
        return new ResponseEntity<>(converter.convertSocialToResponse(social), HttpStatus.OK);
    }

    @PutMapping("/{id}/expert/{expertId}")
    public ResponseEntity<SocialResponse> updateSocial(@Valid @RequestBody SocialRequest requests,
                                                       @Valid @PathVariable(name = "id") Long id,
                                                       @Valid @PathVariable(name = "expertId") Long expertId) throws Exception {
        Social social = socialService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Social not found"));
        Expert expert = expertService.getById(expertId).orElseThrow(() -> new ResourceNotFoundException("Expert not found"));

        social.setExpert(expert);
        social.setUrl(requests.getUrl());
        socialService.update(social);
        return new ResponseEntity<>(converter.convertSocialToResponse(social), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSocial(@Valid @PathVariable(name = "id") Long id) throws Exception{
        Social social = socialService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Social not found"));
        socialService.delete(id);
        return ResponseEntity.ok().build();
    }
}
