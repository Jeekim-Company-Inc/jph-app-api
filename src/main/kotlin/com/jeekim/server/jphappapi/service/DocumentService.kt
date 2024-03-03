package com.jeekim.server.jphappapi.service

import com.jeekim.server.jphappapi.client.bucket.BucketAdapter
import com.jeekim.server.jphappapi.data.ImageUrl
import com.jeekim.server.jphappapi.utils.logger
import org.springframework.stereotype.Service

@Service
class DocumentService(
    private val bucketAdapter: BucketAdapter,
) {
    fun getTerms(): List<ImageUrl> {
        logger().info("매뉴얼 이미지 요청 ")
        val prefix = "manual"
        val types = listOf("edi")
        return types.map { type -> bucketAdapter.getAllResourceUrls(prefix, type) }
    }
}