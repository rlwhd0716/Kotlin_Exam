package kr.com.rlwhd.kotlinexample.retrofit

data class ImageData(
    val meta: Meta,
    val documents: List<ImageDocument>
)

data class VideoData(
    val meta: Meta,
    val documents: List<VideoDocument>
)

data class Meta(
    val total_count: Int, // 검색어에 검색된 문서수
    val pageable_count: Int, // total_count 중에 노출가능 문서수
    val is_end: Boolean // 현재 페이지가 마지막 페이지인지 여부
)

data class ImageDocument(
    val collection: String, // 컬렉션
    val thumbnail_url: String, // 이미지 썸네일 URL
    val image_url: String, // 이미지의 URL
    val width: Int, // 이미지의 가로 크기
    val height: Int, // 이미지의 세로 크기
    val display_sitename: String, // 출처명
    val doc_url: String, // 문서 URL
    val datetime: String, // 이미지 등록일 ISO 8601. [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz],
    var favorite: Boolean = false
)

data class VideoDocument(
    val title: String,
    val url: String,
    val datetime: String,
    val play_time: Int,
    val thumbnail: String,
    val author: String
)