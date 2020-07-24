package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * {
 *   "data": {
 *     "content": [],
 *     "pageable": {
 *       "sort": {
 *         "sorted": true,
 *         "unsorted": false,
 *         "empty": false
 *       },
 *       "offset": 10,
 *       "pageNumber": 1,
 *       "pageSize": 10,
 *       "paged": true,
 *       "unpaged": false
 *     },
 *     "totalPages": 0,
 *     "totalElements": 0,
 *     "last": true,
 *     "sort": {
 *       "sorted": true,
 *       "unsorted": false,
 *       "empty": false
 *     },
 *     "size": 10,
 *     "number": 1,
 *     "numberOfElements": 0,
 *     "first": false,
 *     "empty": true
 *   }
 * }
 */
public class CardUseHistoryResponse {

    @Expose
    @SerializedName("data")
    private History data;

    public History getData() { return data; }

    public static class History {


//        @Expose
//        @SerializedName("content")
//        private List<String> content;

        @Expose
        @SerializedName("pageable")
        private Pageable pageable;


        public static class Pageable {
            /*
            "pageable": {
              "sort": {
                "sorted": true,
                "unsorted": false,
                "empty": false
              },
              "offset": 10,
              "pageNumber": 1,
              "pageSize": 10,
              "paged": true,
              "unpaged": false
            },
                     */

            @Expose
            @SerializedName("offset")
            private int offset;

            public int getOffset() {
                return offset;
            }

            @Expose
            @SerializedName("sort")
            private Sort sort;

            public Sort getSort() { return sort; }


            public static class Sort {

                @Expose
                @SerializedName("sorted")
                private boolean sorted;

                @Expose
                @SerializedName("unsorted")
                private boolean unsorted;

                @Expose
                @SerializedName("empty")
                private boolean empty;

                public boolean isSorted() {
                    return sorted;
                }

                public boolean isUnsorted() {
                    return unsorted;
                }

                public boolean isEmpty() {
                    return empty;
                }


            }
        }

//        public List<String> getContent() {
//            return content;
//        }

        public Pageable getPageable() {
            return pageable;
        }
    }
}
