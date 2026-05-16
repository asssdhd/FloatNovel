package org.example.floatnovel.service;

import org.example.floatnovel.DTO.CatalogueDTO;
import org.example.floatnovel.DTO.ChapterDTO;
import org.example.floatnovel.entity.Result;

import java.io.IOException;
import java.util.List;

public interface ChapterService {


    Result upload(Long novelId, String title, String content, Integer orders) throws IOException;

    Result<List<CatalogueDTO>> Catalogue(Long novelId);

    Result<ChapterDTO> read(Long chapterId,Long novelId);

    Result deleteChapter(Long chapterId);

    Result updateChapter(ChapterDTO chapterDTO);

    Result<ChapterDTO> getChapterInfo(Long novelId, Long chapterId);
}
