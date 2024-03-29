package com.jeekim.server.jphappapi.service

import com.jeekim.server.jphappapi.client.bucket.BucketAdapter
import com.jeekim.server.jphappapi.data.ImageUrl
import org.springframework.stereotype.Service

@Service
class DocumentService(
    private val bucketAdapter: BucketAdapter
) {

    fun getTermsImages(types: List<String>): List<ImageUrl> {
        val prefix = "legalNotice"
        return types.map { type -> bucketAdapter.getAllResourceUrls(prefix, type) }
    }

}