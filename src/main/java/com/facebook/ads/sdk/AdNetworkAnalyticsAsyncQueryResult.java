/**
 * Copyright (c) 2015-present, Facebook, Inc. All rights reserved.
 *
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to
 * use, copy, modify, and distribute this software in source code or binary
 * form for use in connection with the web services and APIs provided by
 * Facebook.
 *
 * As with any software that integrates with the Facebook platform, your use
 * of this software is subject to the Facebook Developer Principles and
 * Policies [http://developers.facebook.com/policy/]. This copyright notice
 * shall be included in all copies or substantial portions of the software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 */

package com.facebook.ads.sdk;

import java.io.File;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.facebook.ads.sdk.APIException.MalformedResponseException;

/**
 * This class is auto-generated.
 *
 * For any issues or feature requests related to this class, please let us know
 * on github and we'll fix in our codegen framework. We'll not be able to accept
 * pull request for this class.
 *
 */
public class AdNetworkAnalyticsAsyncQueryResult extends APINode {
  @SerializedName("data")
  private Object mData = null;
  @SerializedName("error")
  private Object mError = null;
  @SerializedName("query_id")
  private String mQueryId = null;
  @SerializedName("results")
  private List<Object> mResults = null;
  @SerializedName("status")
  private String mStatus = null;
  @SerializedName("id")
  private String mId = null;
  protected static Gson gson = null;

  public AdNetworkAnalyticsAsyncQueryResult() {
  }

  public String getId() {
    return getFieldId().toString();
  }
  public static AdNetworkAnalyticsAsyncQueryResult loadJSON(String json, APIContext context, String header) {
    AdNetworkAnalyticsAsyncQueryResult adNetworkAnalyticsAsyncQueryResult = getGson().fromJson(json, AdNetworkAnalyticsAsyncQueryResult.class);
    if (context.isDebug()) {
      JsonParser parser = new JsonParser();
      JsonElement o1 = parser.parse(json);
      JsonElement o2 = parser.parse(adNetworkAnalyticsAsyncQueryResult.toString());
      if (o1.getAsJsonObject().get("__fb_trace_id__") != null) {
        o2.getAsJsonObject().add("__fb_trace_id__", o1.getAsJsonObject().get("__fb_trace_id__"));
      }
      if (!o1.equals(o2)) {
        context.log("[Warning] When parsing response, object is not consistent with JSON:");
        context.log("[JSON]" + o1);
        context.log("[Object]" + o2);
      };
    }
    adNetworkAnalyticsAsyncQueryResult.context = context;
    adNetworkAnalyticsAsyncQueryResult.rawValue = json;
    adNetworkAnalyticsAsyncQueryResult.header = header;
    return adNetworkAnalyticsAsyncQueryResult;
  }

  public static APINodeList<AdNetworkAnalyticsAsyncQueryResult> parseResponse(String json, APIContext context, APIRequest request, String header) throws MalformedResponseException {
    APINodeList<AdNetworkAnalyticsAsyncQueryResult> adNetworkAnalyticsAsyncQueryResults = new APINodeList<AdNetworkAnalyticsAsyncQueryResult>(request, json, header);
    JsonArray arr;
    JsonObject obj;
    JsonParser parser = new JsonParser();
    Exception exception = null;
    try{
      JsonElement result = parser.parse(json);
      if (result.isJsonArray()) {
        // First, check if it's a pure JSON Array
        arr = result.getAsJsonArray();
        for (int i = 0; i < arr.size(); i++) {
          adNetworkAnalyticsAsyncQueryResults.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context, header));
        };
        return adNetworkAnalyticsAsyncQueryResults;
      } else if (result.isJsonObject()) {
        obj = result.getAsJsonObject();
        if (obj.has("data")) {
          if (obj.has("paging")) {
            JsonObject paging = obj.get("paging").getAsJsonObject();
            if (paging.has("cursors")) {
                JsonObject cursors = paging.get("cursors").getAsJsonObject();
                String before = cursors.has("before") ? cursors.get("before").getAsString() : null;
                String after = cursors.has("after") ? cursors.get("after").getAsString() : null;
                adNetworkAnalyticsAsyncQueryResults.setCursors(before, after);
            }
            String previous = paging.has("previous") ? paging.get("previous").getAsString() : null;
            String next = paging.has("next") ? paging.get("next").getAsString() : null;
            adNetworkAnalyticsAsyncQueryResults.setPaging(previous, next);
            if (context.hasAppSecret()) {
              adNetworkAnalyticsAsyncQueryResults.setAppSecret(context.getAppSecretProof());
            }
          }
          if (obj.get("data").isJsonArray()) {
            // Second, check if it's a JSON array with "data"
            arr = obj.get("data").getAsJsonArray();
            for (int i = 0; i < arr.size(); i++) {
              adNetworkAnalyticsAsyncQueryResults.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context, header));
            };
          } else if (obj.get("data").isJsonObject()) {
            // Third, check if it's a JSON object with "data"
            obj = obj.get("data").getAsJsonObject();
            boolean isRedownload = false;
            for (String s : new String[]{"campaigns", "adsets", "ads"}) {
              if (obj.has(s)) {
                isRedownload = true;
                obj = obj.getAsJsonObject(s);
                for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                  adNetworkAnalyticsAsyncQueryResults.add(loadJSON(entry.getValue().toString(), context, header));
                }
                break;
              }
            }
            if (!isRedownload) {
              adNetworkAnalyticsAsyncQueryResults.add(loadJSON(obj.toString(), context, header));
            }
          }
          return adNetworkAnalyticsAsyncQueryResults;
        } else if (obj.has("images")) {
          // Fourth, check if it's a map of image objects
          obj = obj.get("images").getAsJsonObject();
          for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
              adNetworkAnalyticsAsyncQueryResults.add(loadJSON(entry.getValue().toString(), context, header));
          }
          return adNetworkAnalyticsAsyncQueryResults;
        } else {
          // Fifth, check if it's an array of objects indexed by id
          boolean isIdIndexedArray = true;
          for (Map.Entry entry : obj.entrySet()) {
            String key = (String) entry.getKey();
            if (key.equals("__fb_trace_id__")) {
              continue;
            }
            JsonElement value = (JsonElement) entry.getValue();
            if (
              value != null &&
              value.isJsonObject() &&
              value.getAsJsonObject().has("id") &&
              value.getAsJsonObject().get("id") != null &&
              value.getAsJsonObject().get("id").getAsString().equals(key)
            ) {
              adNetworkAnalyticsAsyncQueryResults.add(loadJSON(value.toString(), context, header));
            } else {
              isIdIndexedArray = false;
              break;
            }
          }
          if (isIdIndexedArray) {
            return adNetworkAnalyticsAsyncQueryResults;
          }

          // Sixth, check if it's pure JsonObject
          adNetworkAnalyticsAsyncQueryResults.clear();
          adNetworkAnalyticsAsyncQueryResults.add(loadJSON(json, context, header));
          return adNetworkAnalyticsAsyncQueryResults;
        }
      }
    } catch (Exception e) {
      exception = e;
    }
    throw new MalformedResponseException(
      "Invalid response string: " + json,
      exception
    );
  }

  @Override
  public APIContext getContext() {
    return context;
  }

  @Override
  public void setContext(APIContext context) {
    this.context = context;
  }

  @Override
  public String toString() {
    return getGson().toJson(this);
  }


  public Object getFieldData() {
    return mData;
  }

  public AdNetworkAnalyticsAsyncQueryResult setFieldData(Object value) {
    this.mData = value;
    return this;
  }

  public Object getFieldError() {
    return mError;
  }

  public AdNetworkAnalyticsAsyncQueryResult setFieldError(Object value) {
    this.mError = value;
    return this;
  }

  public String getFieldQueryId() {
    return mQueryId;
  }

  public AdNetworkAnalyticsAsyncQueryResult setFieldQueryId(String value) {
    this.mQueryId = value;
    return this;
  }

  public List<Object> getFieldResults() {
    return mResults;
  }

  public AdNetworkAnalyticsAsyncQueryResult setFieldResults(List<Object> value) {
    this.mResults = value;
    return this;
  }

  public String getFieldStatus() {
    return mStatus;
  }

  public AdNetworkAnalyticsAsyncQueryResult setFieldStatus(String value) {
    this.mStatus = value;
    return this;
  }

  public String getFieldId() {
    return mId;
  }

  public AdNetworkAnalyticsAsyncQueryResult setFieldId(String value) {
    this.mId = value;
    return this;
  }




  synchronized /*package*/ static Gson getGson() {
    if (gson != null) {
      return gson;
    } else {
      gson = new GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.STATIC)
        .excludeFieldsWithModifiers(Modifier.PROTECTED)
        .disableHtmlEscaping()
        .create();
    }
    return gson;
  }

  public AdNetworkAnalyticsAsyncQueryResult copyFrom(AdNetworkAnalyticsAsyncQueryResult instance) {
    this.mData = instance.mData;
    this.mError = instance.mError;
    this.mQueryId = instance.mQueryId;
    this.mResults = instance.mResults;
    this.mStatus = instance.mStatus;
    this.mId = instance.mId;
    this.context = instance.context;
    this.rawValue = instance.rawValue;
    return this;
  }

  public static APIRequest.ResponseParser<AdNetworkAnalyticsAsyncQueryResult> getParser() {
    return new APIRequest.ResponseParser<AdNetworkAnalyticsAsyncQueryResult>() {
      public APINodeList<AdNetworkAnalyticsAsyncQueryResult> parseResponse(String response, APIContext context, APIRequest<AdNetworkAnalyticsAsyncQueryResult> request, String header) throws MalformedResponseException {
        return AdNetworkAnalyticsAsyncQueryResult.parseResponse(response, context, request, header);
      }
    };
  }
}
