package com.omolleapaza.peruapps.data

import com.omolleapaza.peruapps.data.source.local.db.entity.PostDeletedEntity
import com.omolleapaza.peruapps.data.source.local.db.entity.PostEntity
import com.omolleapaza.peruapps.data.source.remote.response.BasePost
import com.omolleapaza.peruapps.data.source.remote.response.Hit
import com.omolleapaza.peruapps.data.source.remote.response.RenderingContent


object MockupsData {
    private val listPostResponse = listOf(
        Hit(
            author = "Cesar Vallejo",
            comment_text = "&gt;I&#x27;ve often thought about just setting up a table in front of a check cashing store and offering to cash checks for free with my mobile banking app<p>can you deposit other people&#x27;s checks?",
            created_at = "2021-10-08T15:29:06.000Z",
            created_at_i = 1633706946,
            num_comments = null,
            objectID = "28800345",
            parent_id = 28800322,
            points = null,
            story_id = 28796618,
            story_title = "USPS Begins Postal Banking Pilot Program",
            story_text = null,
            story_url = "https://prospect.org/economy/usps-begins-postal-banking-pilot-program/",
            title = null,
            url = null,
        ),
        Hit(
            author = "sct202",
            comment_text = "People can endorse the check over to you on the back, but most banks won&#x27;t accept it on mobile. And then if you want to deposit it in person at the bank, they may require everyone involved to be present because there is the risk of fraud. Last year someone stole a check from my mail, forged the signature and endorsed it to themselves and it got cashed successfully.",
            created_at = "2021-10-08T15:33:03.000Z",
            created_at_i = 1633707183,
            num_comments = null,
            objectID = "28800345",
            parent_id = 28800322,
            points = null,
            story_id = 28796618,
            story_title = "USPS Begins Postal Banking Pilot Program",
            story_text = null,
            story_url = "https://prospect.org/economy/usps-begins-postal-banking-pilot-program/",
            title = null,
            url = null,
        ), Hit(
            author = "BoxOfRain",
            comment_text = "Honestly I&#x27;ll be happy when it becomes available for mobile installations let alone their IPO. I know it&#x27;s mostly aimed at land-based installations where the fixed infrastructure is poor, but they&#x27;ll be making boat dwellers of various kinds very happy!",
            created_at = "2021-10-08T15:35:04.000Z",
            created_at_i = 1633707304,
            num_comments = null,
            objectID = "28800411",
            parent_id = 28800098,
            points = null,
            story_id = 28799633,
            story_title = "SpaceX hits \$100B valuation after secondary share sale",
            story_text = null,
            story_url = "https://www.cnbc.com/2021/10/08/elon-musks-spacex-valuation-100-billion.html",
            title = null,
            url = null,
        ), Hit(
            author = "llaolleh",
            comment_text = "store and offering to cash checks for free with my mobile banking app<p>can you deposit other people&#x27;s checks?",
            created_at = "2021-10-08T15:38:13.000Z",
            created_at_i = 1633707493,
            num_comments = null,
            objectID = "28800345",
            parent_id = 28800096,
            points = null,
            story_id = 28797485,
            story_title = "Things I’ve learned in my 20 years as a software engineer",
            story_text = null,
            story_url = "https://www.simplethread.com/20-things-ive-learned-in-my-20-years-as-a-software-engineer/",
            title = null,
            url = null,
        ), Hit(
            author = "InvaderFizz",
            comment_text = "Considering they are already in the process of certifying mobile terminals, I would say late 2022 for larger boats and planes. 2023 for something you or I could buy for RV&#x2F;Boat use",
            created_at = "2021-10-08T16:05:54.000Z",
            created_at_i = 1633709154,
            num_comments = null,
            objectID = "28800345",
            parent_id = 28800322,
            points = null,
            story_id = 28799633,
            story_title = "USPS Begins Postal Banking Pilot Program",
            story_text = null,
            story_url = "https://prospect.org/economy/usps-begins-postal-banking-pilot-program/",
            title = null,
            url = null,
        )
    )
    val basePost = BasePost(
        exhaustiveNbHits = false,
        exhaustiveTypo = false,
        hits = listPostResponse,
        hitsPerPage = 20,
        nbHits = 10,
        nbPages = 5,
        page = 0,
        params = "",
        processingTimeMS = 1000,
        message = null,
        query = "mobile",
        renderingContent = RenderingContent()
    )
    val listPostEntity = listOf(
        PostEntity(
            id = "28796618",
            storyTitle = "USPS Begins Postal Banking Pilot Program",
            createdAt = "2021-10-08T15:29:06.000Z",
            author = "Cesar Vallejo",
            storyUrl = "https://prospect.org/economy/usps-begins-postal-banking-pilot-program/",
            commentText = "&gt;I&#x27;ve often thought about just setting up a table in front of a check cashing store and offering to cash checks for free with my mobile banking app<p>can you deposit other people&#x27;s checks?"
        )
    )
    val listPostDeletedEntity = listOf(
        PostDeletedEntity(
            id = "28796618",
            createdAt = "2021-10-08T15:29:06.000Z",
        ),
        PostDeletedEntity(
            id = "28796618",
            createdAt = "2021-10-08T15:29:06.000Z",
        )
    )



}