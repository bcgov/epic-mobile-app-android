package com.airsenze.eaomvp.api;

import com.airsenze.eaomvp.models.Project;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Aidan Laing on 2017-03-21.
 *
 */

public interface EpicApi {

    @GET("projects/published")
    Observable<List<Project>> getProjects();

    @GET("project/bycode/{code}")
    Observable<Project> getProject(@Path("code") String code);

    /*@GET("document/{id}/fetch")
    Observable<Document> fetchDocument(@Path("id") String id);

    @GET("query/document")
    Observable<Document> queryDocument(@Query("project") String project, @Query("directoryID") int directoryID);*/

}
