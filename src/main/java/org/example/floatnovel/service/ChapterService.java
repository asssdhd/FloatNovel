package org.example.floatnovel.service;

import org.example.floatnovel.DTO.CatalogueDTO;
import org.example.floatnovel.DTO.ChapterDTO;
import org.example.floatnovel.entity.Chapter;
import org.example.floatnovel.entity.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ChapterService {


    Result upload(Long novelId, String title, MultipartFile file, Integer orders) throws IOException;

    Result<List<CatalogueDTO>> Catalogue(Long novelId);

    Result<ChapterDTO> read(Long chapterId);
}
