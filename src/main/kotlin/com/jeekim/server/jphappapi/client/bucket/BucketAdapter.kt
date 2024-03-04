package com.jeekim.server.jphappapi.client.bucket

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.*
import com.jeekim.server.jphappapi.data.ImageUrl
import com.jeekim.server.jphappapi.exception.ErrorCode
import com.jeekim.server.jphappapi.exception.JphBizException
import com.jeekim.server.jphappapi.utils.logger

class BucketAdapter(
    private val amazonS3Client: AmazonS3Client,
    private val bucketName: String
) {
    fun getAllResourceUrls(prefix: String, folderName: String): ImageUrl {
        val folderPath = "$prefix/$folderName/"
        logger().info("folderPath: {}", folderPath)
        val listObjectsRequest = ListObjectsRequest(
            bucketName,
            folderPath,
            null,
            null,
            null
        )
        val result: ObjectListing = amazonS3Client.listObjects(listObjectsRequest)
        val resourceUrls = result.objectSummaries.map { obj -> getResourceUrl(obj.key) }

        if (resourceUrls.isEmpty()) {
            throw JphBizException(ErrorCode.TERMS_NAME_IS_NOT_MATCH)
        }
        val filtered = resourceUrls.filter { it: String ->
            it.endsWith(".jpg") || it.endsWith(".jpeg") || it.endsWith(".png")
        }.sorted()
        if (filtered.isEmpty()) {
            throw JphBizException(ErrorCode.TERMS_IMAGES_NOT_EXISTS)
        }
        return ImageUrl(folderName, filtered)
    }
    private fun getResourceUrl(fileKey: String): String {
        return amazonS3Client.getUrl(bucketName, fileKey).toString()
    }

}