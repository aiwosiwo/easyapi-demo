package com.easyapi.test.controller;

import com.easyapi.core.annotation.DocIgnore;
import com.easyapi.test.model.ApiResult;
import com.easyapi.test.model.DocQuery;
import com.easyapi.test.model.Document;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

/**
 * sample controller
 **/
@RestController
@RequestMapping("/sample")
public class SampleController {
    /**
     * Retrieve document list by pagination
     * @param docQuery Document pagination query parameters
     * @return Paginated data
     */
    @PostMapping("/page")
    public ApiResult<PageInfo<Document>> pageList(@RequestBody @Valid DocQuery docQuery) {
        Document document = new Document();
        document.setId(1L);
        document.setTitle("breaking news");
        document.setContent("breaking content");
        PageInfo<Document> pageInfo = new PageInfo<>(Collections.singletonList(document));
        return ApiResult.success(pageInfo);
    }

    /**
     * Get document details
     * @param documentId Document ID
     * @return Document Details
     */
    @GetMapping("/getDocment")
    public ApiResult<Document> document(@RequestParam Long documentId) {
        Document document = new Document();
        document.setId(1L);
        document.setTitle("老汉");
        document.setContent("推*");
        return ApiResult.success(document);
    }

    /**
     * Update document
     * @param document document information
     * @return Update Results
     */
    @PutMapping("/updateDocument")
    public ApiResult<Boolean> updateDocument(@RequestBody Document document) {
        return ApiResult.success(Boolean.TRUE);
    }

    /**
     * remove document
     * @param documentId Document ID
     * @return Delete result
     */

    @DeleteMapping("/delDocument")
    public ApiResult<Boolean> delDocument(@RequestParam Long documentId) {
        return ApiResult.success(Boolean.TRUE);
    }
}
