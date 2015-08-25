package com.example.souseexample2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.io.FileWriter; 
import java.io.IOException; 
import java.io.RandomAccessFile;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.json.JSONTokener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.Queue;
import java.util.LinkedList;

import com.geili.koudai.util.SafeUtil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.mindpipe.android.logging.log4j.LogConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class MainActivity extends Activity {

	//private HttpParams httpParams;
	static TextView textView, textView2, textView3, textView4;
	Button btn1;
	//String newData = "W3siaWQiOiIiLCJhY3Rpb24iOiJvcGVuIiwidXNlcklEIjoiIiwidGltZSI6MTQxOTI2NjQ0NTA3Niwic291cmNlIjoiIiwidHlwZSI6ImFwcCJ9XQ==";
	
	// /listSearchCategories.do
	// 获取大分类主页
	//String newData = "w4xeRP9vuZa27RHQ7zvti5lnHJE5OlhAnAHnslIyTQPuTqgpd33ulI%2B19QetksAt5VNbO0F%2BEyDOAepRzYC%2FdgL76MZshJmRJqblCK7N%2F8noh0qVMuIpJXElRyKdERcNcMgw5f%2F37waeIbVfD7THWBYFWT50XeMyT7Mx8FKleG%2Bx3Nhy5lnrGmcRzC9Ita1OWFq4%2ByqUw73ACIGD1RliJg5ok84FGfohhIi5zEBRoEhaKXwaipHZyfwvZuEudWOvxf4%2FdDhPD7qw9WNCyo1bGu7ryOBjucGOTD9rZVzjOSl7pTr0m3n2wPo6e%2BSOY4H8uwc8IfVnpewY1pojWrOFOf8ZKVND4RIBaIY31Sr1RFRI84Ox5ffO%2BwPBIMbJEFgF2dF7I2K6hpQbjQ2M6d1NnQaTvnRudelkTX%2Fo%2Fd67T7LBbIaznifS3oSt5IRMSzCxrVjVVZ2%2BHRHbQh0gGgKSOIJ%2BF%2FfPOQjlvA%2BC9PYWSs%2FVlbib2kf20sxMEAKTtWT0ej1M3NX%2FE3gCOXQ14aUo%2Bp8Ncc2Z3EDfh0tfLncdHYf0ooqWNl0utJ9S0j6rKEMv19mYiEFpGt3yQ5Ho%2FM%2BK13rgcDD8DZ8StXeGCIQC1KAF7%2F%2B1e2z0KfRFMmpXIZ%2BoBmsvXyyB1GsRGrWYxe8yboIRC%2BUnMc6HPHru6pme68tA7zSHDl%2FyNtvXeB5gghaWbFQ1LDMANEtkuEc2aF0g21FNnO3mkQl%2B8QokAGFk08rrG8ZnJLvhUynY%2FE4XzXGYzoUYuViS4ifG0cmhPFqJsE6Uv3Eo85LVNXB4MXhAsT8HUbABW8UGYZ5ecJlBi2R63pA8PxeaTBBX7mLh1ihiGIDljZfWFNIuAmas9LviruwO3KwDkE94hWXZnc4JBYxDuZK6ia84dehCBP9P3yaRoUOWHqjwHQrAksiFso5YvHD3II7VubDLXRRmYeMzgqqW%2FRbPnW26MsI0XJgWjnAXsw%3D%3D";
	// 获取女装子分类
	//String newData = "w4xeRP9vuZa27RHQ7zvtixLgqZf9kh7YTUVB8In5aKsSbq3xRXqNWw8%2F%2F%2BUAwY%2FuNEb8YJqLv58Jy%2Bt4O40VgPSQtKw5RpRB5rDDTREtBaWW9yR%2B%2Fc7PQrLeWTPvWfMQ8qtZ4Xbuwzb7z3tEhh12s90Fl3%2F7bXSn7bh0%2F2tnnKd6qrtuycMiQIMVmq%2BRpcXX7fV3P7NL8gD6aE7ke62DHM2vamaWycbcWg1ldHn%2BFTPbrVyz6zZTVRFNo3djOz1gINaqA94oCLpRGdCgjkaQn6M4weaZ%2BoWcbMJXxJ%2BiteC0fgMae9iwGTsB6tLNZEdSqmmmsUA4gwBQh9XbBIR86u5wnUo%2BE8OsAcl53gE0UQ4KMiR0dllFtflIQjtFak506vpGP5beQJqzLwSsF7iJU8uFwZqpkavp%2BjM8GVdddW7d9F258Ch4JMPI907Pg2yfDkgbymNP0JskRCCHZgeuUSOXVqh9j7lkYM6zbbM9h9OjD32%2FfCExYEKX4X5pk3voJDDYtsv1s3xl6jSVG6pfLqXsNcsfS39SQjRUk3IdsYSvRrWoo7QeZKIUOOq6DAexFkWA6N2rlPT%2F5S%2FQvPz88gmYWjkPFDAjHbmIpQrsV0gmYKPb7CmMum2TfWSvzQWfgTAMV7XjKMru7zNFWmdf2AAIq2UjKZHiIJsaVmaavSSQEpVO%2BiYn8Vtq1RKeY196%2Bu7ObzWvkxP6M3qsr33KuS5HXR7Ol4w4eIZkMYKXj8SI3jWGLeAtvqb4Jn28c1XYnwc0yUERNi327fidpquOsFq7eNI5Z8eze1SkRqMZcUD3o8EUCnuGVwx%2B%2BIlFbqQq7%2B38h2UoRGn2o2s6li9R2WfREL1KDe4jfVjIaEaI9Ac9RRk%2FGOCA2dqUa5zUSvJjUTnMPuft%2BdQgJSyfuwsSZ9J%2BB0h6PKDzm5qMFgmmZoH9ZcH%2FGR9SE%2B6PpgrFebP2q6VPfRV6l7uLKdvGbnIBZPZa90GJGpmdQF2n1xrgyzA%3D";
	// 获取女鞋子分类
	//String newData = "w4xeRP9vuZa27RHQ7zvtixLgqZf9kh7YTUVB8In5aKvcsL8JsvDgM2yE8Y92OcSVitlUlFh2qAS9XLn4gnl%2BmvSQtKw5RpRB5rDDTREtBaWW9yR%2B%2Fc7PQrLeWTPvWfMQ8qtZ4Xbuwzb7z3tEhh12s90Fl3%2F7bXSn7bh0%2F2tnnKd6qrtuycMiQIMVmq%2BRpcXX7fV3P7NL8gD6aE7ke62DHM2vamaWycbcWg1ldHn%2BFTPbrVyz6zZTVRFNo3djOz1gINaqA94oCLpRGdCgjkaQn6M4weaZ%2BoWcbMJXxJ%2BiteC0fgMae9iwGTsB6tLNZEdSqmmmsUA4gwBQh9XbBIR86u5wnUo%2BE8OsAcl53gE0UQ4KMiR0dllFtflIQjtFak506vpGP5beQJqzLwSsF7iJU8uFwZqpkavp%2BjM8GVdddW7d9F258Ch4JMPI907Pg2yfDkgbymNP0JskRCCHZgeuUSOXVqh9j7lkYM6zbbM9h9OjD32%2FfCExYEKX4X5pk3voJDDYtsv1s3xl6jSVG6pfLqXsNcsfS39SQjRUk3IdsYSvRrWoo7QeZKIUOOq6DAexFkWA6N2rlPT%2F5S%2FQvPz88gmYWjkPFDAjHbmIpQrsV0i%2BeINFjD6FQKMZcdMnRXABwG8r27Q0MGnE3o4ABbQX2gAIq2UjKZHiIJsaVmaavSSQEpVO%2BiYn8Vtq1RKeY196%2Bu7ObzWvkxP6M3qsr33KuS5HXR7Ol4w4eIZkMYKXj8SI3jWGLeAtvqb4Jn28c1XYnwc0yUERNi327fidpquOsFq7eNI5Z8eze1SkRqMZcUD3o8EUCnuGVwx%2B%2BIlFbqQq7%2B38h2UoRGn2o2s6li9R2WfREL1KDe4jfVjIaEaI9Ac9RRk%2FGOCA2dqUa5zUSvJjUTnMPuft%2BdQgJSyfuwsSZ9J%2BB0h6PKDzm5qMFgmmZoH9ZcH%2FGR9SE%2B6PpgrFebP2q6VPfRV6l7uLKdvGbnIBZPZa90GJGpmdQF2n1xrgyzA%3D";
	// 获取箱包子分类
	//String newData = "w4xeRP9vuZa27RHQ7zvtixLgqZf9kh7YTUVB8In5aKuo254eaG%2BI1xX8vAYT9SVAitlUlFh2qAS9XLn4gnl%2BmvSQtKw5RpRB5rDDTREtBaWW9yR%2B%2Fc7PQrLeWTPvWfMQ8qtZ4Xbuwzb7z3tEhh12s90Fl3%2F7bXSn7bh0%2F2tnnKd6qrtuycMiQIMVmq%2BRpcXX7fV3P7NL8gD6aE7ke62DHM2vamaWycbcWg1ldHn%2BFTPbrVyz6zZTVRFNo3djOz1gINaqA94oCLpRGdCgjkaQn6M4weaZ%2BoWcbMJXxJ%2BiteC0fgMae9iwGTsB6tLNZEdSqmmmsUA4gwBQh9XbBIR86u5wnUo%2BE8OsAcl53gE0UQ4KMiR0dllFtflIQjtFak506vpGP5beQJqzLwSsF7iJU8uFwZqpkavp%2BjM8GVdddW7d9F258Ch4JMPI907Pg2yfDkgbymNP0JskRCCHZgeuUSOXVqh9j7lkYM6zbbM9h9OjD32%2FfCExYEKX4X5pk3voJDDYtsv1s3xl6jSVG6pfLqXsNcsfS39SQjRUk3IdsYSvRrWoo7QeZKIUOOq6DAexFkWA6N2rlPT%2F5S%2FQvPz88gmYWjkPFDAjHbmIpQrsV0gyE9vEDouWgzuTjtDNZa%2BXg6wtvPhWrrtBCpCAk5PlYQAIq2UjKZHiIJsaVmaavSSQEpVO%2BiYn8Vtq1RKeY196%2Bu7ObzWvkxP6M3qsr33KuS5HXR7Ol4w4eIZkMYKXj8SI3jWGLeAtvqb4Jn28c1XYnwc0yUERNi327fidpquOsFq7eNI5Z8eze1SkRqMZcUD3o8EUCnuGVwx%2B%2BIlFbqQq7%2B38h2UoRGn2o2s6li9R2WfREL1KDe4jfVjIaEaI9Ac9RRk%2FGOCA2dqUa5zUSvJjUTnMPuft%2BdQgJSyfuwsSZ9J%2BB0h6PKDzm5qMFgmmZoH9ZcH%2FGR9SE%2B6PpgrFebP2q6VPfRV6l7uLKdvGbnIBZPZa90GJGpmdQF2n1xrgyzA%3D";
	// 获取手提包列表
	//String newData = "72pICm%2BhnI1mwy%2BFV4fSfrCV6nZ8Itj%2BYvDmyuSokog0SWK%2FPhNMDicmqApQEllUWiTGsJBR8uIOtn6o7zxruw5vBcjPB%2FOJ6u7MhgIDy9tsk2xAZuVuNzHVtWdKOllFkQBDW4X1vRhxrGRgq2dQnLwpZO1AJmuKAJRXX%2Fpl8AyQnYyeEOLQsvHNLlrUOBwgV5FTWjoUudhLp2Osvf5IhDyhBgGaI9FcMI6g81pIOYFrq9c%2BfxzFgEa7D0OZVzLrCpLRFSybLCeJoa2YizORuBTQpSfNmjViqSfjpzixyd5PTygEi28H9LXGx%2BpmM6R1uH%2BokyzWmJFIaZtjZbUKLsESUj7uKszBzppXUzg9n8BDHQz1xU32mVKJ2iGkmiU1VU8JZCyqM%2BjH1QPhUxQ6GO%2FMP3GIPp8ry4J4BrScHj9QNayd4FmeoUVEZ9iV2pRo71FL9pyHlSaPMKp8h0g8opuOAfIEWq2II6qW8Wwvip9VyswuOzTcor2RNYKQhsma2F9HswR%2BqZGAvJ5j1WaDqv6Ah4ME4qQMKLkBPupeZ4E6B%2BgK0ehKFOg6fJgO2acyEnZsUcYiUSWbllNhTZtlqKa1%2F8niaqGlBj%2FjwikrpPw%2F3ddHOLwtDfGcxVDIoSJ0B5fKYMO5zi86xEUJbYNU2KTMM4DJQ7PkAyqOhPVKkFm6ZpqtmFF7sGq2LdyhhCDPcrjSBIYIfgC6Y9ii5i5YrxtMAhTJPCHQULpLTOQuEBsY94J6DjwgLpzv6NKuMv%2BmlvoG%2BJsCruJUEYH19MmCNJfH%2FetjtG0gLqX3Zysb%2FKLoq0lyIDEHWI3G9d3CD%2FqtQVVRV71fry8qbDoVTpm7i%2Fj8jSHgCbEtfUaSEdA6gp9ONSIPzQjCeSbqd6skU8HOQxtgE%2BJf0yx2RpGR25BSM0mtbaEnmaSaKhz1m4sKMKO%2Ff43CycZPLwC1XFB414CA21QKsBymBjBP0VUfF4E774KMrUrP80G8ZdgrihXtA%2BGMbBhulRh2LiaJRiAwQ2oSAWEymZsCGYYb34UfQNZtybOTA0RtTtjsDw28X5kYtLPwVvG9VvG9cWajkRFbsgWr%2FnvXm0GJ53iwG6z9TrGXKvj6MPsE36jxswNaARP57WdY%2B9M84Rbo94NTS8SaBgEageeydwNpqYdPndPoOtVwLA%3D%3D";
	// 获取某个手提包详细页面
	//String newData = "FGx1wYg9xQKvNCrc%2B%2BJ2k8bSXT1Yi7%2F7tVjbbl8AGeAeizuwT9Q4DGsOUYc75K6AO1zWjIL3k%2FfD1amHvjZnZgf2yW7SK7XavJZtV2%2FubniG15dvMUeRACzHSQxDotCNtlcA0UmFpXuv%2Bsffd7QCi%2F0kl5XBExtK1Ea7SZ8TD9GyHJCLbftHaX1qLNGCtRhW4Tz1nkFGrlj3yskZWBmtoEdzO54l7%2BXd%2FXeIrhepVq6Tuu9W15AEPG7cmUzDWg3fO%2BnC3kzkyHcjZzKO5svqf53fSmVfSQZdPgXuL%2Bi1SIcyySdGTj7bBoGVRa9Vd%2BZfoxDS8mMeeqsKcvPKDeNbZcnbxmrmk09WMfnnEH0IrolsaaSalRRJatoo%2F8QTclB%2FenyhDl6jPlO%2ForxhLsi%2F1cGWhnWGYvgcEjz4mp6UHTg%2B5qgQLOgKs1W489L5rhBfnc2E7CLxEDEQ0%2B7xi%2F1tfyQ9fK5oxxAB0%2BxEC0KUku0tMsy4dE9d5WQ0qp%2FVRMsX3C%2FeD9bylvSZOgTR8UM6GdTEb5%2Bin%2B5hLaB0fITOCSul6Rzui8gd1BGZvW%2B68odN%2FXhwi6zE41B8kNyQCrAdKQ61hnvyGZW31fYYmTA7vFfxAjucPCwEx3ui9Q91Y79aoHTacn0ccG9ioGKce2yNeCIrrIih1g9E1OrZFOWawFA9hn9jz0ilbR1ElGAzw1CxIuyFqGWRiPPDvDgsPDENzQHrvKQ36ZGZzPQX1fyDoEkr8ZETWTQVdH98nUHzB%2B7Dr3BJm8cLK3Q%2B2CT8NFoVbu%2FexviPrIY3zCDQOZBueLxXkyoCKDwxAp6Vay1bc%2B3Xm7ZAzdlxlNCG9byz6lRD0F%2F%2BKhDXbNp3f4VOP3shilDejYUozTpLha8KOOTVynRSqCFMYcnZ6YiM0a50Mfhbp3WJoUwU2Ycbpoi7l9MdpLJKCuM1DRbT5g4Ak3JVrhg8jvLE%2FQj18hY3Tlc5V%2FjSvLeEVEnckuDT2IlE%2FbqVVfgaxPslvs3gDKF0MsFV4z89KaoU%2Fi6BaAWScqlr2ubYGtVdmWFbuve5RhaP5QMymUk%3D";
	// 获取苏州毛衣
	//String strSuzhouMaoyi = "72pICm%2BhnI1mwy%2BFV4fSfrCV6nZ8Itj%2BYvDmyuSokohjl3T90zcUI360gXhL3j%2FC96xKjvrmTK%2B1kXUs3%2BOL3pZnJR%2Ba9X%2BFaDSvXWCzlnjMbBURdneuYX5qKhRtAO5ttIygry7pGcJean6zIG3X0Aiwc6eDku2OajpfK3VS%2BR4f218WvN2hQVydnLyodBk9R5UBpXpZckh2InvQAvGMt85W%2B1l4ftYy1nR4jNGPxAm7%2BijavEdU151j9itFIisnaPMEDDsCD4FKTgLx58S6p08pe51XF0TNbpicECc3PjimWcG7X9Jpl9ZwsWob44pmM0fke19VPTkwPsN%2FJW41U6fqCXWD%2BU4%2Fg9zv%2B3wmtYjoV4Tq6Nme5buab73V3JrimUudKpmagtHW2CKWSYx84yTurB26hYDrBiDkQ69fOT33lRRk926Zq%2BAMkVekn%2FGu9xpOLw5AySwS0ZT5hyPxmmMOLmIFDLs5xfQiylONE4NQ4N6mqK2173j0kn6%2BZzCLm0yrVhsIHfwq2taU3k3PRVBfblMCkE9Ise7EPE4cAqJUEh%2BA20y3i0RzWYqEYkdcXZ6l%2Fxgwa4NVkSbK9j3G9Fj3bq7CxvZbsZQb0GGPdPTreWAy794gsBRalA%2F1kNEr7yszCTxhz%2BitHyGbg%2F8IrZ3y3OM99xjkCpIvRbWXC2oaaDV6S%2B%2FyLDk76cFGx2abUUTNI1A0nCv2JQKjXMaVD6YWW3zxpp4JwgMSFiF3EbYRA73kym8qb5%2FILMXqFQNmufSpbB%2FROjq49CZvk3C5VynSnvpv6ZlIShhT5owH63haBp5KArM%2FIA088weeBACWepaChMaGLkHbXafGGdxFY89UCmGgsHNF%2Bsgo1PH5WxSofS1Bin0SitSr9GbSU7%2Be9Ooz9A1zUb8r%2Fn2dSgJ0T7Iuyv%2FqDYT7Q2tRTcvbD4Aeexx27sNY3GxVZRro1Pw5u%2BY0Lv%2Fbf%2BmjEiZFXruymzJIOxQLEnnKwMY2elQ6khSlQg491QhHBh%2FPZlxTbhNbsr17Mz9IdRINHGw4EclVfopFXtQxTAdClGnWVa3UbM0x7tbMIsgO6c2jq1eaaIqUMQl%2FkWEpBjnBMeZFc6BZpxHUVbIJFb89r7Gkkm7irncoa71UsnwVza35W%2B%2BvGCJilWbI9peBvW%2F%2BaXaZOiyAPD2S8dJbKNO31hoicQRITIlYnJv2WFEh85q7SKbT12%2BxcxnXb0x%2FWSc%2Bz8dIcR3RX%2B7I7Erj%2F3mbLDDthHC2tLw%3D";
	//String newData = "FGx1wYg9xQKvNCrc%2B%2BJ2k99izjlBbSiCOpO8Lvx8YUXAR4tNyrHAccrDvSZ44P5ECA27Kkm%2BDNd5qMMhAjTC5HXUcIqMRpaKHZJTQO%2FVdb%2BziW5ADVnLm4ExfNdl6unmqCGy2hZahJsFINiTNlotTiboy7nKzx%2F7abyMn3DMfI1K4qV0xekCUr55JLlQN%2FRR6RUkURpwT3xtPzsCzUPrKL040cnumtXblCLTTVc33aALCYkC6aX4OGJL1vD5N%2BSIgJ6DYjmL9%2Bu9lpHvIILf0oKmSCOhvZigBgCv5HXPii3fljNgNwyuj0UKO8evMy%2FJfvL5C0Vs4pgkOlfQHzyqPudVZSuNieY2cwMz8o15p%2BWRalJH7tBeGGHjMArl0yMFC7ta0P4E9d%2Br4MEv%2BNLLQO0LVpssLLu%2FDdayuvBMglJvrwoiXvXhzde0CpYSac%2F3PVoR%2BX7%2F%2B%2F7wLt1fxOeBKEoCGJBrIzVb%2F7iWWdClF6lTBvGhBF9O263i9sVWDsNvQxVRycznW9UUJNlUCjbm0VA3ZtHPJknOAd0wx9w2IyVn%2Fuoq6XS%2BPnY%2B0ZVKV7gHogmLKmLhTt8W0B62nmBHqxVmnghNtV04KepNPncBUvWvwFSnU0%2FcDcJYzU9Txi7JIuHGdJgBtkH3YmbAStd9fLe5HhdYGCpWLkVYf60%2BQDACd6HdlmOA6CC6t%2BQx2KDJxjwcbo9Fm%2BPJ3Dy7nXqqP9o4r5Oyk1jW8VYDIO8UYyiFINAklDUfwZ8oUXeOiYyyl6%2BVm7ylg81NdOQi9c7MdjxG37CEbpcMLPG2EL9a89I8HYsazcEc27%2BRD13iPXpdLt15LGCiCkLM3It1LOU65D2KcTQERX7FQX%2BAlneMc3haKTdF0NbWF05615riYUQhmEl0E9GxaKMaNiAWUrcozM1Ga3OykuPfk6AwhLoY%2BFta9SnH48%2BOI5HuIYmCT9tQyTA%2FZ96OfCQvPcaQMDJG2OARJDaj6savy4bqDrtNrheN1buDCDwW3s6a5kAjVxILFkWA6N2rlPT%2F5S%2FQvPz88jFPQcmfS0ujd9LsYKI33MA%3D";
	// data="\"{\"body\":{\"productSource\":\"w2c\"},\"header\":{\"netsubtype\":\"3_UMTS\",\"wduserID\":\"\",\"appid\":\"com.koudai.weidian.buyer\",\"mac\":\"\",\"build\":\"t141210223756\",\"islogin\":\"1\",\"serial_no\":\"unknown\",\"network\":\"3G\",\"version\":\"1.2.0\",\"android_id\":\"99ab3f322d636e6d\",\"kduss\":\"\",\"gender\":\"2\",\"wduss\":\"\",\"dpi\":\"240\",\"apiv\":\"32\",\"platform\":\"android\",\"os\":\"17\",\"wifiID\":\"\",\"imei\":\"000000000000000\",\"loc\":\"\",\"sessionid\":\"1419052937314_463357\",\"appstatus\":\"active\",\"h\":\"800\",\"w\":\"480\",\"guid\":\"\",\"userID\":\"kc3-kdb33404da-e7ad-479c-af23-495825056661\",\"mStation\":\"mcc=310&mnc=260&lac=10000&cellId=10000\",\"brand\":\"generic\",\"mid\":\"sdk\",\"imsi\":\"310260000000000\",\"channel\":\"1010n\",\"anony\":\"\"}}";
	//String data = "{\"body\":{\"newData\":\"W3siaWQiOiIiLCJhY3Rpb24iOiJvcGVuIiwidXNlcklEIjoiIiwidGltZSI6MTQxOTI2NjQ0NTA3Niwic291cmNlIjoiIiwidHlwZSI6ImFwcCJ9XQ==\",\"productSource\":\"w2c\"},\"header\":{\"netsubtype\":\"3_UMTS\",\"wduserID\":\"\",\"appid\":\"com.koudai.weidian.buyer\",\"mac\":\"\",\"build\":\"t141210223756\",\"islogin\":\"1\",\"serial_no\":\"unknown\",\"network\":\"3G\",\"version\":\"1.2.0\",\"android_id\":\"99ab3f322d636e6d\",\"kduss\":\"8db3420df94635622a485bd22add4acb\",\"gender\":\"2\",\"wduss\":\"\",\"dpi\":\"240\",\"apiv\":\"32\",\"platform\":\"android\",\"os\":\"17\",\"wifiID\":\"\",\"imei\":\"000000000000000\",\"loc\":\"\",\"sessionid\":\"1419266659978_593294\",\"appstatus\":\"active\",\"h\":\"800\",\"w\":\"480\",\"guid\":\"1419266503705_1055996\",\"userID\":\"288722267\",\"mStation\":\"mcc=310&mnc=260&lac=10000&cellId=10000\",\"brand\":\"generic\",\"mid\":\"sdk\",\"imsi\":\"310260000000000\",\"channel\":\"1010n\",\"anony\":\"@anonymous:_000000000000000\"}}";
	RandomAccessFile randomFile;
	
	//int bNeedGetSubCate = 1;
	RandomAccessFile subcateFile;
	
	RandomAccessFile cityFile;
	
	//static int bMobilePhoneEncode = 0;
	
	int MSG_CITY = 0,
		MSG_CATE = 1,
		MSG_SUBCATE = 2,
		MSG_PRODUCT = 3;
	static Handler mHandler = new Handler(){	      
	    @Override
	    public void handleMessage(Message msg) {
	    	//这里就可以从msg取出你从线程中传到UI线程的东西； 
	    	String strMessage = (String)msg.obj;
	    	String txt = strMessage;
	    	/*if(bMobilePhoneEncode == 1)
	    	{
	    		try{
		    		txt = new String(strMessage.getBytes("utf-8"), "gbk");
		    	}catch(Exception e)
		    	{
		    		e.printStackTrace();
		    	}
	    	}
	    	*/
	    	
	    	switch(msg.what)
	    	{
	    	case 0:
	    		textView.setText("CITY STATUS: " + txt);
	    		break;
	    	case 1:
	    		textView2.setText("CATEGORY: " + txt);
	    		break;
	    	case 2:
	    		textView3.setText("SUBCATEGORY: " + txt);
	    		break;
	    	case 3:
	    		textView4.setText("PRODUCT: "+ txt);
	    		break;
    		default:
    			break;
	    	}
	    	
	    }
	};
	
	public class Category{
		public String cateID;
		public String cateName;
	}
	
	public class SubCategory{
		public String subCateID;
		public String subCateName;
		public String midCateID;
		public String midCateName;
		public String cateID;
		public String cateName;
	}
	
	Queue<Category> queueCategory = new LinkedList<Category>();
	Category currentCategory;
	Queue<String> queueCity = new LinkedList<String>();
	String dataFileName = "vdian_data_debug.csv";
	String currentCity = "debug";
	Queue<SubCategory> queueSubCategory = new LinkedList<SubCategory>();
	SubCategory currentSubCategory;
	Queue<String> queueProduct = new LinkedList<String>();
	String currentProduct;
	
	RandomAccessFile infoFile;
	String lastCity = "";
	String lastCate = "";
	String lastSubCate = "";
	String lastPage = "";
	String lastProductID = "";
	int bStartGetCity = 0;
	int bStartGetCate = 0;
	int bStartGetSubCate = 0;
	int bStartGetPage = 0;
	int bStartGetProduct = 0;
	
	int bRunning = 0;
	private Logger gLogger;
    
	//final LogConfigurator writerConfigurator = new LogConfigurator();
	final LogConfigurator logConfigurator = new LogConfigurator();
	public void configLog()
	{	
		//writerConfigurator.setFileName(Environment.getExternalStorageDirectory() + File.separator + "kuaidi.data");
		logConfigurator.setFileName(Environment.getExternalStorageDirectory() + File.separator + dataFileName);
		// Set the root log level
		//writerConfigurator.setRootLevel(Level.DEBUG);
		logConfigurator.setRootLevel(Level.DEBUG);
		///writerConfigurator.setMaxFileSize(1024 * 1024 * 500);
		logConfigurator.setMaxFileSize(1024 * 1024 * 1024);
		// Set log level of a specific logger
		//writerConfigurator.setLevel("org.apache", Level.ERROR);
		logConfigurator.setLevel("org.apache", Level.ERROR);
		logConfigurator.setImmediateFlush(true);
		//writerConfigurator.configure();
		logConfigurator.configure();

		//gLogger = Logger.getLogger(this.getClass());
		//gWriter = 
		gLogger = Logger.getLogger("vdian");
	}
	// String
	// data="{\"body\":{\"newData\":\"W3siaWQiOiIiLCJhY3Rpb24iOiJvcGVuIiwidXNlcklEIjoiIiwidGltZSI6MTQxOTMwMjU0NTg3Niwic291cmNlIjoiIiwidHlwZSI6ImFwcCJ9XQ==\",\"productSource\":\"w2c\"},\"header\":{\"netsubtype\":\"3_UMTS\",\"wduserID\":\"\",\"appid\":\"com.koudai.weidian.buyer\",\"mac\":\"\",\"build\":\"t141210223756\",\"islogin\":\"1\",\"serial_no\":\"unknown\",\"network\":\"3G\",\"version\":\"1.2.0\",\"android_id\":\"99ab3f322d636e6d\",\"kduss\":\"436e7eda4abd9b87e0da3f5e417a5eaa\",\"gender\":\"2\",\"wduss\":\"\",\"dpi\":\"240\",\"apiv\":\"32\",\"platform\":\"android\",\"os\":\"17\",\"wifiID\":\"\",\"imei\":\"000000000000000\",\"loc\":\"\",\"sessionid\":\"1419302546100_707400\",\"appstatus\":\"active\",\"h\":\"800\",\"w\":\"480\",\"guid\":\"1419302556151_947549\",\"userID\":\"289465887\",\"mStation\":\"mcc=310&mnc=260&lac=10000&cellId=10000\",\"brand\":\"generic\",\"mid\":\"sdk\",\"imsi\":\"310260000000000\",\"channel\":\"1010n\",\"anony\":\"@anonymous:_000000000000000\"}}";
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); 
		
		configLog();
		gLogger.debug("test android log to file in sd card using log4j");
		
		textView = (TextView) findViewById(R.id.text);
		textView2 = (TextView) findViewById(R.id.text2);
		textView3 = (TextView) findViewById(R.id.text3);
		textView4 = (TextView) findViewById(R.id.text4);
		btn1 = (Button)findViewById(R.id.button1);
		btn1.setText("开始");
		
		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Thread t;
				if(bRunning == 0)
				{
					bRunning = 1;
					btn1.setText("停止");
					t = new Thread(new HTTPThread());
					t.start();
				}else
				{
					bRunning = 0;
					btn1.setText("开始");
				}
				
			}
		});
		
		System.loadLibrary("safe-so-ed-by-cyg");
		
		//Log.i("tag", data);

		int result = SafeUtil.readSignature(this, "1.0.0");
		String string =	result + "";
		textView.setText("Signature: " + string);

		/*
		// 绗笁涓弬鏁版槸鏁版嵁鍖呬腑鐨刱id瀛楁鍊�		
		byte[] encry = SafeUtil.encryptRequestData(
				this.getApplicationContext(), data.getBytes(), "3.0.1");
		String str_encry = SafeUtil.Convert(encry);
		//str_encry = "8yQjU308s7QGG9xDfn1NOsvKMl8pHPbmL0/mEDEte4YQgm2HPLWwUMa5GtrK2HPZ8ej4XR2ujV5c03oTBhh/LvXolfSam1NGiTRxbkTLY/dVdQI3qeLNUWBrkod9TvirjISn8W8dLKicLDs/j90fMh9wimXb0pGp1G53dVlV5GtBhcTsd8uI8lloGIyErzG4ZxMqFGYEHR0Ok19PxXz6cVDbCbTGekgcB/HtbWsR8s60zlEvkXrud05f+ictxRbvH/c5Gevp44HTXinwmNd+xA3X8AzZ7iWh3V+riqBWbPexBoEHpUG52caE5m2ojDL8DWh8GirTHZPmhbo+j1dE3UDiaXwzd/GYERtOcHFbgsRbn3zc/VOy4KTTkDAPHsTyahS/+/CyTLsuNP3h/7payoDYST8t7lSAtQJkJiRgWdBvYMGgUujI4zOhfrXnL3QB2fpX4QcgcmqDIiXu2wnoTppThPqKxdnK5nt4UdoDPzhCExEoI3Cbo+LM7QxF3HwpP5tbUP1O1+ZT4avNv82hDbke7pgPMCGiqLT9koPRhsvCChM4K1lZRIWG18/Vy8EkfUdEdFeoxcKBj6/q5KS1EefxpgXHZsR0v2+nQPH4MgCUxFEfEKXTw+7bOXAp0uy6BT95E6VkLvHuq5+Bq7THlhUUse1ZQ8aUJukRD3xOEKRHsFqBDC05EE3qakcCiDz3+UUqIB9YImFP7cYuzPNImf2+Qf8aAkh/3k1KAFjIlFfO8dTn8hShPy9qBZuhuWw4ha9mqSPqeHnNY9HgHYkBXnkcjfPvSLAGWkAkScnvM5irDUX8bjJsUqTHRw6NVtxwpvoAybXJl0GmtIA3ldYukgOhABREvG5advd58lw8V+JjHrMlhYD1FwwmkoADeaghtwHAqd9ryt52+iIDwWOUllLUqllAFWlVylLWi34WT7WGQQuq3yoar7MGeQPqj57w5+Xl599EsIdCGu0bBSYOMDxh5Sm4Dz/zkHkY9NpBJ9pYWVlxHE3mEkGISuD0dvSgNDmwbKBs3282MSZUNI/BvoxutPoZF73VN+UqW9apTw7Ikn7lBvpM0yNYiNm/U3WP+Vkacz48ZtSm8wKOchjqu4XtUDTpn8WVBmTXwK3KhBk=";
		
		//str_encry = "72pICm%2BhnI1mwy%2BFV4fSfrCV6nZ8Itj%2BYvDmyuSokohjl3T90zcUI360gXhL3j%2FC96xKjvrmTK%2B1kXUs3%2BOL3pZnJR%2Ba9X%2BFaDSvXWCzlnjMbBURdneuYX5qKhRtAO5ttIygry7pGcJean6zIG3X0Aiwc6eDku2OajpfK3VS%2BR4f218WvN2hQVydnLyodBk9R5UBpXpZckh2InvQAvGMt85W%2B1l4ftYy1nR4jNGPxAm7%2BijavEdU151j9itFIisnaPMEDDsCD4FKTgLx58S6p08pe51XF0TNbpicECc3Pjga%2BG2wwd7B6rJCiL0MwrvpM0fke19VPTkwPsN%2FJW41U6fqCXWD%2BU4%2Fg9zv%2B3wmtYjoV4Tq6Nme5buab73V3JrimUudKpmagtHW2CKWSYx84yTurB26hYDrBiDkQ69fOT33lRRk926Zq%2BAMkVekn%2FGu9xpOLw5AySwS0ZT5hyPxmmMOLmIFDLs5xfQiylONE4NQ4N6mqK2173j0kn6%2BZzCLm0yrVhsIHfwq2taU3k3PRVBfblMCkE9Ise7EPE4cAqJUEh%2BA20y3i0RzWYqEYkdcXZ6l%2Fxgwa4NVkSbK9j3G9Fj3bq7CxvZbsZQb0GGPdPTreWAy794gsBRalA%2F1kNEr7yszCTxhz%2BitHyGbg%2F8IrZ3y3OM99xjkCpIvRbWXC2oaaDV6S%2B%2FyLDk76cFGx2abUUTNI1A0nCv2JQKjXMaVD6YWW3zxpp4JwgMSFiF3EbYRA73kym8qb5%2FILMXqFQNmufSpbB%2FROjq49CZvk3C5VynSnvpv6ZlIShhT5owH63haBp5KArM%2FIA088weeBACWepaChMaGLkHbXafGGdxFY89UCmGgsHNF%2Bsgo1PH5WxSiemnAGrHJMNCLSCBQ0JwWfDSRA2ROYqzlHRoCN708%2BbIuyv%2FqDYT7Q2tRTcvbD4Aeexx27sNY3GxVZRro1Pw5u%2BY0Lv%2Fbf%2BmjEiZFXruymzJIOxQLEnnKwMY2elQ6khSlQg491QhHBh%2FPZlxTbhNbsr17Mz9IdRINHGw4EclVfopFXtQxTAdClGnWVa3UbM0x7tbMIsgO6c2jq1eaaIqUMQl%2FkWEpBjnBMeZFc6BZpxHUVbIJFb89r7Gkkm7irncoa71UsnwVza35W%2B%2BvGCJilWbI9peBvW%2F%2BaXaZOiyAPD2S8dJbKNO31hoicQRITIlYnJv2WFEh85q7SKbT12%2BxcxnXb0x%2FWSc%2Bz8dIcR3RX%2B7I7Erj%2F3mbLDDthHC2tLw%3D";
		
		str_encry = newData;
		*/
		String str_encry = "w4xeRP9vuZa27RHQ7zvti05zC6lyrjqyMnKIVTEwrWokemYYxxAnBqamBD0KqmD0MLjoQ2Ns2Z8KmpvpbPgA30yll9qWfoSkrt1BWcblx6SogybffEijODmjTZOyoSRgPWj5bOIKlUUpq7%2FipOBa390LS5LeEESac3chlER0nJi4v%2BUB5Rsz2MZhxHdFddaNZd%2BGx9OD6ulMWMh7uOb5Ht97p8U6ne97%2FLgVkq9PLHifs6kWOOQyh6%2F%2BKRL976%2BEuFhWT1pgtrqxkEt9MoND5HQgKAZhoqbfIf8hlMRjTa1YbQncJ8XPP7WTX5eC%2BbRrEOnqCOdOh3zFPa%2BovQK7HkwvSwiL%2Fwovnn9PJVid7%2FGYHlRcctrgJlvXLXOz29PJR2nNvT3JFpqUcIFD5b9FU4R5APAKaKg89kLcd9jUjrSyH8g%2BROYmI%2FdKVmcu7MyGV4XyRCdhEBVFMmxfCUBvMf%2FtzWjLtfgzomyzdRp0ykDPKrfZlVj6gmyH6MFvqnhhBpO%2BdG516WRNf%2Bj93rtPssdpaBj%2FNVRuI%2FjnGj6Q4bwg%2FuQXef%2BhxrZulLJ%2BLF9bf0Oopzo6Zj%2BVPyzNziQQ4NWVuJvaR%2FbSzEwQApO1ZPTCpXJIYoYvn6AsXRq4NP8Snh3cGiLhVOFlo3c3SqSeEcZ0k2RwbrBCbLTY4AkrbwUT0%2B4qt01oVX12q2mn5XvWmWUJ6S6TnAFzN5XJg40msIcIaa2x6BTkPe7wdlmS3ky7FwK7pDLbGFskdiiWib3tfsip1OOYxAuMyNV8lNNRBAHrvKQ36ZGZzPQX1fyDoEm6NOEswF%2BvlLw%2B8fL%2FX1dMqIBb6uWkRJ9BdxZysllss7ffst7tIDkgd84%2FeHREzEPjZFZCRm5wi57c7VINVanasY7noM5VnWIeQZdiPzl0yC85edtS3vvYRZr7Nkuf6Wgx7tbMIsgO6c2jq1eaaIqUMQl%2FkWEpBjnBMeZFc6BZpwlG1p2oBg1sD7l4WIF%2F7prZx6ecfrUro2DkWdSj4odb9AmaNaUFAK2cOmr7HNoMoxlG5T5zik%2BB3TYShjhlMeONENE2ex6KT8MGlYBNiZtbvA9yd4yCuFq4SCSYuk9cOAl27KATwsTLz6rO5Wb0ZNt3Nz5j4KCepY3mOPiiwV8MbTYmm2MD12A666R8SVBwOg%3D%3D";
		
		try {
			str_encry = URLDecoder.decode(str_encry, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		textView2.setText(str_encry);
		Log.i("encry", str_encry);
		writeData("encry");
		writeData(str_encry);
		
		byte[] test_enc = SafeUtil.ReConvert(str_encry.getBytes());
		byte[] test_denc;
		test_denc = SafeUtil.decryptRequestData(null, test_enc, "1.8.0");
		String str_test_denc = new String(test_denc);
		Log.i("decry--", str_test_denc);
		/*
		writeData("decry--");
		writeData(str_test_denc);
		
		String reEncry = EncryptString(str_test_denc);
		Log.i("reEncry--", reEncry);
		
		textView4.setText(new String(test_denc));
		try {

			textView3.setText(URLEncoder.encode(str_encry, "utf-8"));
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e1){
			e1.printStackTrace();
		}

		// 瑙ｅ瘑鏁版嵁
		byte[] dencry;
		dencry = SafeUtil.decryptRequestData(null, encry, "3.0.1");
	 	*/

	}
	
	private void saveInfo(String city, String cate, String subCate, String page, String index)
	{
		try
		{
			String fileName = Environment.getExternalStorageDirectory().getPath() + "/vdian_info.txt";
			File file = new File(fileName);
			if(file.exists())
			{
				file.delete();
			}
			infoFile = new RandomAccessFile(fileName, "rw"); 
			String strInfo = "0000" + "," + city + "," + cate + "," + subCate + "," + page + "," + index;
			infoFile.writeBytes(new String(strInfo.getBytes("GBK"), "ISO8859_1"));
			infoFile.writeBytes("\r\n");
			infoFile.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void loadInfo()
	{
		try
		{
			infoFile = new RandomAccessFile(Environment.getExternalStorageDirectory().getPath() + "/vdian_info.txt", "rw"); 
			if(infoFile.read() != -1){
			 	String strInfoLine = new String(infoFile.readLine().getBytes("ISO8859_1"),"GBK");
			 	String[] infos = strInfoLine.split(",");

			 	lastCity = infos[1];
				lastCate = infos[2];
				lastSubCate = infos[3];
				lastPage = infos[4];
				lastProductID = infos[5];
		 	}else{
		 		bStartGetCity = 1;
		 		bStartGetCate = 1;
		 		bStartGetSubCate = 1;
		 		bStartGetPage = 1;
		 		bStartGetProduct = 1;
		 	}
			infoFile.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/*private void writeSubCate(String subcate)
	{
		try { 
			subcateFile = new RandomAccessFile(Environment.getExternalStorageDirectory().getPath() + "/vdian_subcate.txt", "rw");
			long len = subcateFile.length();
			subcateFile.seek(len);
			String tochar = new String(subcate.getBytes("GBK"), "ISO8859_1");
			subcateFile.writeBytes(tochar);
			subcateFile.writeBytes("\r\n");
			subcateFile.close();
		} catch (IOException e)
		{ 
			e.printStackTrace(); 
		}
	}*/
	
	private void writeData(String data)
	{
		String newDataFileName = "vdian_data_" + currentCity + ".csv";
		if(!dataFileName.equalsIgnoreCase(newDataFileName))
		{
			dataFileName = newDataFileName;
			configLog();
		}
		
		gLogger.debug(data);
	}

	/*private void writeData(String data)
	{
		try {
			// 打开一个随机访问文件流，按读写方式 
			randomFile = new RandomAccessFile(Environment.getExternalStorageDirectory().getPath() + "/vdian_data_"+currentCity+".txt", "rw"); 
			// 文件长度，字节数 
			long fileLength = randomFile.length(); 
			//将写文件指针移到文件尾。 
			randomFile.seek(fileLength);
			
			randomFile.write(data.getBytes("gb2312"));
			randomFile.writeBytes("\r\n");
			randomFile.close();
		} catch (IOException e)
		{ 
			e.printStackTrace(); 
		} 
	}*/
	
	
	// 获取所有城市
	public void GetAllCity(){
		
		try
		{
			cityFile = new RandomAccessFile(Environment.getExternalStorageDirectory().getPath() + "/vdian_city.txt", "rw"); 
			while(cityFile.read() != -1){
			 	String strCity = new String(cityFile.readLine().getBytes("ISO-8859-1"),"GBK");
			 	String[] temps = strCity.split(",");
			 	if(temps.length >= 2)
			 	{
			 		queueCity.offer(temps[1]);
			 	}
		 	}
			cityFile.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		/*
		queueCity.offer("北京");
		queueCity.offer("成都");
		queueCity.offer("广州");
		queueCity.offer("杭州");
		queueCity.offer("上海");
		queueCity.offer("深圳");
		*/
	}
	
	// 获取所有大类
	public void GetAllCategories(){
		// /listSearchCategories.do
		// 获取大分类主页
		String edata = FormatStrGetRootCate();//"w4xeRP9vuZa27RHQ7zvti5lnHJE5OlhAnAHnslIyTQPuTqgpd33ulI%2B19QetksAt5VNbO0F%2BEyDOAepRzYC%2FdgL76MZshJmRJqblCK7N%2F8noh0qVMuIpJXElRyKdERcNcMgw5f%2F37waeIbVfD7THWBYFWT50XeMyT7Mx8FKleG%2Bx3Nhy5lnrGmcRzC9Ita1OWFq4%2ByqUw73ACIGD1RliJg5ok84FGfohhIi5zEBRoEhaKXwaipHZyfwvZuEudWOvxf4%2FdDhPD7qw9WNCyo1bGu7ryOBjucGOTD9rZVzjOSl7pTr0m3n2wPo6e%2BSOY4H8uwc8IfVnpewY1pojWrOFOf8ZKVND4RIBaIY31Sr1RFRI84Ox5ffO%2BwPBIMbJEFgF2dF7I2K6hpQbjQ2M6d1NnQaTvnRudelkTX%2Fo%2Fd67T7LBbIaznifS3oSt5IRMSzCxrVjVVZ2%2BHRHbQh0gGgKSOIJ%2BF%2FfPOQjlvA%2BC9PYWSs%2FVlbib2kf20sxMEAKTtWT0ej1M3NX%2FE3gCOXQ14aUo%2Bp8Ncc2Z3EDfh0tfLncdHYf0ooqWNl0utJ9S0j6rKEMv19mYiEFpGt3yQ5Ho%2FM%2BK13rgcDD8DZ8StXeGCIQC1KAF7%2F%2B1e2z0KfRFMmpXIZ%2BoBmsvXyyB1GsRGrWYxe8yboIRC%2BUnMc6HPHru6pme68tA7zSHDl%2FyNtvXeB5gghaWbFQ1LDMANEtkuEc2aF0g21FNnO3mkQl%2B8QokAGFk08rrG8ZnJLvhUynY%2FE4XzXGYzoUYuViS4ifG0cmhPFqJsE6Uv3Eo85LVNXB4MXhAsT8HUbABW8UGYZ5ecJlBi2R63pA8PxeaTBBX7mLh1ihiGIDljZfWFNIuAmas9LviruwO3KwDkE94hWXZnc4JBYxDuZK6ia84dehCBP9P3yaRoUOWHqjwHQrAksiFso5YvHD3II7VubDLXRRmYeMzgqqW%2FRbPnW26MsI0XJgWjnAXsw%3D%3D";		
		String strResult = HttpPostData("/appserver_getRootCategories.do"/*"/listSearchCategories.do"*/, edata);
		// todo: 解析json push category到队列
		try{
			JSONTokener jsonParser = new JSONTokener(strResult);
			JSONObject jsonObj = (JSONObject) jsonParser.nextValue();
			JSONObject resultObj = jsonObj.getJSONObject("result");
			JSONObject dataObj = resultObj.getJSONObject("data");
			JSONArray topCategories = dataObj.getJSONArray("categories");
			JSONObject rootCategory = topCategories.getJSONObject(0);
			JSONArray categories = rootCategory.getJSONArray("sub_categories");
			
			for(int i = 0;i < categories.length(); i++){
				JSONObject category = categories.getJSONObject(i);
				String cateID = category.getString("id");
				String cateName = category.getString("name");
				// 检查cate是否已经抓去过
				if(bStartGetCate == 0)
				{
					if(!cateName.equals(lastCate))
					{
						Log.i("Cate-Duplicated", "currentCate: " + cateName + " lastCate: " + lastCate);
						continue;
					}else
					{
						bStartGetCate = 1;
					}
				}
				Category cate = new Category();
				cate.cateID = cateID;
				cate.cateName = cateName;
				queueCategory.offer(cate);
			}
		}catch(JSONException e)
		{
			e.printStackTrace();
		}
	}
	
	// 获取所有子类
	public void GetAllSubcategories(){
		// /listSearchCategories.do
		// 每个大类
		for(currentCategory = queueCategory.poll(); null != currentCategory; currentCategory = queueCategory.poll())
		{
			UpdateUI(MSG_SUBCATE, "Getting Subcategories of: " + currentCategory.cateName);
			String strSubcategoies = GetSubCategories(currentCategory.cateID);
			try{
				JSONTokener jsonParser = new JSONTokener(strSubcategoies);
				JSONObject jsonObj = (JSONObject) jsonParser.nextValue();
				JSONObject resultObj = jsonObj.getJSONObject("result");
				JSONObject dataObj = resultObj.getJSONObject("data");
				JSONArray categoies = dataObj.getJSONArray("categories");
				for(int i = 0;i < categoies.length(); i++){
					JSONObject category = categoies.getJSONObject(i);
					String cateID = "Na";
					String cateName = category.getString("name");
					JSONArray subcategoies = category.getJSONArray("sub_categories");
					for(int j = 0; j < subcategoies.length(); j++){
						JSONObject subcategory = subcategoies.getJSONObject(j);
						String subcateID = subcategory.getString("id");
						String subcateName = subcategory.getString("name");
						// 检查subcate是否已经抓去过
						if(bStartGetSubCate == 0)
						{
							if(!subcateName.equals(lastSubCate))
							{
								Log.i("SubCate-Duplicated", "currentSubCate: " + subcateName + " lastSubCate: " + lastSubCate);
								continue;
							}else
							{
								bStartGetSubCate = 1;
							}
						}
						SubCategory subcate = new SubCategory();
						subcate.subCateID = subcateID;
						subcate.subCateName = subcateName;
						subcate.midCateID = cateID;
						subcate.midCateName = cateName;
						subcate.cateID = currentCategory.cateID;
						subcate.cateName = currentCategory.cateName;
						queueSubCategory.offer(subcate);
					//	String subcateLine = currentCategory.cateID + "," + currentCategory.cateName + "," + cateID + "," + cateName + "," + subcateID + "," + subcateName;
					//	writeSubCate(subcateLine);
					}
				}
			}catch(JSONException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/*private int SubCateFileExists()
	{
		File file = new File(Environment.getExternalStorageDirectory().getPath() + "/vdian_subcate.txt");
		if(file.exists())
		{
			UpdateUI(MSG_CATE, "sub cate file exists");
			return 0;
		}else
		{
			UpdateUI(MSG_CATE, "sub cate file not exists");
			return 1;
		}
	}
	*/
	/*
	private void ReadAllSubcateFormFile()
	{
		try{
			subcateFile = new RandomAccessFile(Environment.getExternalStorageDirectory().getPath() + "/vdian_subcate.txt", "rw");
			while(subcateFile.read()!=-1){
			 	String strSubcate = new String(subcateFile.readLine().getBytes("ISO8859_1"),"GBK");
			 	String[] subcatePros = strSubcate.split(",");
			 	SubCategory subcate = new SubCategory();
				subcate.cateID = subcatePros[0];
				subcate.cateName = subcatePros[1];
				subcate.midCateID = subcatePros[2];
				subcate.midCateName = subcatePros[3];
				subcate.subCateID = subcatePros[4];
				subcate.subCateName = subcatePros[5];
				
				// 检查cate是否已经抓去过
				if(bStartGetCate == 0 || bStartGetSubCate == 0)
				{
					if(!subcate.cateName.equals(lastCate) 
							|| !subcate.subCateName.equals(lastSubCate))
					{
						Log.i("Cate-Duplicated", "currentCate: " + subcate.cateName + "-" + subcate.subCateName + " lastCate: " + lastCate + "-" + lastSubCate);
						UpdateUI(MSG_CITY, "Cate-Duplicated [currentCate: " + subcate.cateName + "-" + subcate.subCateName + " lastCate: " + lastCate + "-" + lastSubCate + "]");
						continue;
					}else
					{
						bStartGetCate = 1;
						bStartGetSubCate = 1;
					}
				}
					
				queueSubCategory.offer(subcate);
		 	}
			subcateFile.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	*/
	// 获取某个category的sub
	public String GetSubCategories(String parentID)
	{
		String edata = FormatStrGetSubCate(parentID);
		return HttpPostData("/appserver_getSubCategories.do"/*"/listSearchCategories.do"*/, edata);
	}
	
	// 获取某个subcategory的product列表
	public String GetProductsList(String city, String cateID, String cateName, String subCateID, String subCateName, String page)
	{
		String edata = FormatStrGetProductList(city, cateID, cateName, subCateID, subCateName, page);
		return HttpPostData("/proxy_searchItems.do", edata);
	}
	
	// 获取product detail
	public String GetProductDetail(String productID, String subCateName)
	{
		String edata = FormatStrGetProductDetail(productID, subCateName);
		
		return HttpPostData("/appserver_getItemInfo.do"/*"/getItemInfo_v2.do"*/, edata);
	}

	public void UpdateUI(int type, String info)
	{
		//下面new一个Message用来装你要传到UI线程的东西
	     Message msg = new Message();
	     msg.what =  type;
	     msg.obj = info;
	    //mHandler 见下面定义
	     mHandler.sendMessage(msg);
	}
	
	public class HTTPThread implements Runnable {

		@Override
		public void run() {
			
			
			//String str_suzhou_maoyi_list = GetProductsList("苏州", "001", "女装", "11456", "毛衣", "100");
			
			// TODO Auto-generated method stub
			Log.i("Status", "Getting All City");
			UpdateUI(MSG_CITY, "Getting All City");
			GetAllCity();
			
			//int bCategoryGot = 0;
			
			// 加载上一次抓到得最后一个商品
			UpdateUI(MSG_CITY, "Loading Last Time Info");
			loadInfo();
			
			//bNeedGetSubCate = SubCateFileExists();
			
			// 每个城市
			for(currentCity = queueCity.poll(); null != currentCity; currentCity = queueCity.poll())
			{
				// 检查city是否已经抓取过
				if(bStartGetCity == 0)
				{
					if(!currentCity.equals(lastCity))
					{
						Log.i("City-Duplicated", "currentCity: " + currentCity + " lastCity: " + lastCity);
						UpdateUI(MSG_CITY, "City-Duplicated [currentCity: " + currentCity + " lastCity: " + lastCity + "]");
						continue;
					}else
					{
						bStartGetCity = 1;
					}
				}
				Log.i("City", currentCity);
				UpdateUI(MSG_CITY, currentCity);
				
				// 仅一次  读文件或者网络
				//if(bCategoryGot == 0)
				//{
				//	bCategoryGot = 1;
					//if(bNeedGetSubCate == 1){
					Log.i("Status", "Getting All Categories");
					UpdateUI(MSG_CATE, "Getting All Categories");
					GetAllCategories();
					Log.i("Status", "Getting All Subcategories");
					GetAllSubcategories();
					//	bNeedGetSubCate = 0;
					//}else
					//{
					//	UpdateUI(MSG_SUBCATE, "Reading All Subcategories from file...");
					//	ReadAllSubcateFormFile();
					//}
				//}
				
				// 每个子类
				int subCateNo = 0;
				for (currentSubCategory = queueSubCategory.poll(); null != currentSubCategory; currentSubCategory = queueSubCategory.poll())
				{
					//Log.i("Cate", currentSubCategory.cateName + "-" + currentSubCategory.midCateName + "-" + currentSubCategory.subCateName);
					UpdateUI(MSG_CATE, currentSubCategory.cateName);
					subCateNo++;
					UpdateUI(MSG_SUBCATE, currentSubCategory.midCateName + "-" + currentSubCategory.subCateName + "-" + subCateNo);
					// 请求当前城市某个子类的商品列表
					int lastPageGot = 0;
					int page = 0;
					while(0 == lastPageGot)
					{	
						page++;
						// 检查page是否已经抓去过
						if(bStartGetPage == 0)
						{
							if(!lastPage.equals("" + page))
							{
								Log.i("Page-Duplicated", "currentPage: " + page + " lastPage: " + lastPage);
								UpdateUI(MSG_SUBCATE, "Page-Duplicated [currentPage: " + page + " lastPage: " + lastPage + "]");
								continue;
							}else
							{
								bStartGetPage = 1;
							}
						}
						UpdateUI(MSG_SUBCATE, currentSubCategory.midCateName + "-" + currentSubCategory.subCateName + "-" + subCateNo + "-" + page);
						String strProductList = GetProductsList(currentCity, currentSubCategory.cateID, currentSubCategory.cateName, currentSubCategory.subCateID, currentSubCategory.subCateName, "" + page);
						// 解析子类商品列表
						try{
							JSONTokener jsonParser = new JSONTokener(strProductList);
							JSONObject jsonObj = (JSONObject) jsonParser.nextValue();
							JSONObject status = jsonObj.getJSONObject("status");
							String strStatusCode = status.getString("code");
							if(!strStatusCode.equals("0"))
							{
								Log.e("ProductList", "status: " + strStatusCode);
								if(bStartGetPage == 1 && bStartGetProduct == 0)
								{
									bStartGetProduct = 1;
								}
								continue;
							}
							JSONObject result = jsonObj.getJSONObject("result");
							JSONObject data = result.getJSONObject("data");
							JSONArray items = data.getJSONArray("items");
							if(items.length() == 0)
							{
								lastPageGot = 1;
								
								if(bStartGetPage == 1 && bStartGetProduct == 0)
								{
									bStartGetProduct = 1;
								}
								continue;
							}
							
							for(int i = 0; i < items.length(); i++)
							{
								JSONObject product = items.getJSONObject(i);
								String productID = product.getString("product_id");
								// 检查product是否已经抓取过
								if(bStartGetProduct == 0)
								{
									bStartGetProduct = 1;
									/* 这里取消productid 重复检查   由于每次列表返回次序不同
									if(!lastProductID.equals(productID))
									{
										Log.i("Product-Duplicated", "currentProduct: " + productID + " " + "lastProductID: " + lastProductID );
										UpdateUI(MSG_PRODUCT, "Product-Duplicated [currentProduct: " + productID + " " + "lastProductID: " + lastProductID + "]" );
										continue;
									}else
									{
										bStartGetProduct = 1;
									}
									*/
								}
								queueProduct.offer(productID);
							}
							if(bRunning == 0)
							{
								return;
							}
						}
						catch(JSONException e)
						{
							e.printStackTrace();
							Log.e("ProductList", "Invalid product list response json");
							Log.e("JSON", strProductList);
							UpdateUI(MSG_PRODUCT, "Invalid ProductList Json: "+ strProductList);
							continue;
						}
						// 每个商品
						for(currentProduct = queueProduct.poll(); null != currentProduct; currentProduct = queueProduct.poll())
						{
							//Log.i("Product", currentSubCategory.cateName + "-" + currentSubCategory.midCateName + "-" + currentSubCategory.subCateName + "-" + currentProduct);
							//UpdateUI(MSG_PRODUCT, currentProduct );
							// 请求商品详细页
							String strProduct = GetProductDetail(currentProduct, currentSubCategory.subCateName);
							if(strProduct.length() <= 5)
							{
								UpdateUI(MSG_PRODUCT, currentProduct + ": request failed!!" );
								continue;
							}
							// 解析商品详细页
							try{
								JSONTokener jsonParser1 = new JSONTokener(strProduct);
								JSONObject jsonObj1 = (JSONObject) jsonParser1.nextValue();
								JSONObject status1 = jsonObj1.getJSONObject("status");
								String strStatusCode1 = status1.getString("code");
								if(!strStatusCode1.equals("0"))
								{
									Log.e("Product", "Invalid product detail");
									continue;
								}
								JSONObject result1 = jsonObj1.getJSONObject("result");
								JSONObject data1 = result1.getJSONObject("data");
								JSONObject item1 = data1.getJSONObject("item");
								String product_Name = item1.getString("name").replaceAll(",", "").replaceAll("\r", "").replaceAll("\n", "");
								String product_ID = item1.getString("product_id").replaceAll(",", "").replaceAll("\r", "").replaceAll("\n", "");
								String product_Price = item1.getString("price").replaceAll(",", "").replaceAll("\r", "").replaceAll("\n", "");
								//String product_msrPrice = item1.getString("msrprice").replaceAll(",", "").replaceAll("\r", "").replaceAll("\n", "");
								String product_soldout = item1.getString("soldout").replaceAll(",", "").replaceAll("\r", "").replaceAll("\n", "");
								int favourites = item1.getInt("favorite_count");
								
								JSONObject exentd = item1.getJSONObject("extend");
								int comments = exentd.getInt("comment_count");
								
								JSONObject shop = data1.getJSONObject("shop");
								String shop_name = shop.getString("shop_name").replaceAll(",", "").replaceAll("\r", "").replaceAll("\n", "");
								String shop_id = shop.getString("shop_id").replaceAll(",", "").replaceAll("\r", "").replaceAll("\n", "");
								String location = shop.getString("place_name").replaceAll(",", "").replaceAll("\r", "").replaceAll("\n", "");
								
								String cate_name = currentSubCategory.cateName;
								String mid_cate_name = currentSubCategory.midCateName;
								String sub_cate_name = currentSubCategory.subCateName;
								/*if(1 == bMobilePhoneEncode)
								{
									try{
										cate_name = new String(currentSubCategory.cateName.getBytes("utf-8"), "gbk");
										mid_cate_name = new String(currentSubCategory.midCateName.getBytes("utf-8"), "gbk");
										sub_cate_name = new String(currentSubCategory.subCateName.getBytes("utf-8"), "gbk");
									}catch(Exception e)
									{
										e.printStackTrace();
									}	
								}*/

								String strProductLine = currentCity + ", " +
										cate_name + "," + currentSubCategory.cateID + "," +
										mid_cate_name + "," + currentSubCategory.midCateID + "," +
										sub_cate_name + "," + currentSubCategory.subCateID + "," +
										product_Name + "," +
										product_ID + "," +
										product_Price + "," +
										"Na" + "," +
										product_soldout + "," +
										favourites + "," +
										comments + "," +
										shop_name + "," +
										shop_id + "," +
										location;
								
								writeData(strProductLine);
								saveInfo(currentCity, currentSubCategory.cateName, currentSubCategory.subCateName, "" + page, currentProduct);
								//UpdateUI(MSG_PRODUCT, strProductLine );
								UpdateUI(MSG_PRODUCT, currentProduct );
								if(bRunning == 0)
								{
									return;
								}
							}catch(JSONException e1)
							{
								e1.printStackTrace();
								Log.e("Product", "Invalid product detail response json");
								Log.e("JSON", strProduct);
								UpdateUI(MSG_PRODUCT, "Invalid Product Json: "+ strProduct);
								continue;
							}
						}
					}
				}
				//bCategoryGot = 0;
			}
			UpdateUI(MSG_PRODUCT, "Spider finished successfully!!");
		}
		
	}
	
	private String FormatStrGetRootCate()
	{
		String edata_plain = "{\"body\":{\"productSource\":\"w2c\"},\"header\":{\"netsubtype\":\"0_\",\"wduserID\":\"\",\"appid\":\"com.koudai.weidian.buyer\",\"mac\":\"58:7f:66:95:f3:32\",\"build\":\"buildnum\",\"islogin\":\"1\",\"serial_no\":\"P4M7N15326043570\",\"network\":\"WIFI\",\"version\":\"2.2.0\",\"android_id\":\"28f524c904ec7b2e\",\"kduss\":\"c11bb0e7eb751884679521a173d8777c\",\"gender\":\"2\",\"wduss\":\"\",\"user_id\":\"475118685\",\"dpi\":\"480\",\"apiv\":\"45\",\"platform\":\"android\",\"os\":\"19\",\"wifiID\":\"\",\"imei\":\"865166028760026\",\"loc\":\"120.404428,31.226557,0.0\",\"sessionid\":\"ks_3_1436615211590_707281\",\"appstatus\":\"active\",\"h\":\"1812\",\"w\":\"1080\",\"guid\":\"1436613930120_8898674\",\"userID\":\"475118685\",\"mStation\":\"mcc=460&mnc=1&lac=10000&cellId=10000\",\"brand\":\"Huawei\",\"mid\":\"HUAWEI_MT7-TL00\",\"imsi\":\"460011510506624\",\"channel\":\"1000n\",\"anony\":\"@anonymous:58:7f:66:95:f3:32_865166028760026\"}}";
		String edata = EncryptString(edata_plain);
		return edata;
	}
	
	private String FormatStrGetSubCate(String parentID)
	{
		String edata_plain = "{\"body\":{\"productSource\":\"w2c\",\"parent_id\":\"" + 
							parentID + 
							/*"\"},\"header\":{\"netsubtype\":\"3_UMTS\",\"wduserID\":\"\",\"appid\":\"com.koudai.weidian.buyer\",\"mac\":\"\",\"build\":\"20150210113510\",\"islogin\":\"1\",\"serial_no\":\"unknown\",\"network\":\"3G\",\"version\":\"1.8.0\",\"android_id\":\"549ad29c7d2ce3b0\",\"kduss\":\"cf5200247abb66e22232c16e11a1e8b8\",\"gender\":\"2\",\"wduss\":\"\",\"user_id\":\"346264345\",\"dpi\":\"320\",\"apiv\":\"39\",\"platform\":\"android\",\"os\":\"17\",\"wifiID\":\"\",\"imei\":\"000000000000000\",\"loc\":\"\",\"sessionid\":\"1425994143494_357291\",\"appstatus\":\"active\",\"h\":\"1184\",\"w\":\"720\",\"guid\":\"1423993007870_7189536\",\"userID\":\"346264345\",\"mStation\":\"mcc=310&mnc=260&lac=10000&cellId=10000\",\"brand\":\"generic\",\"mid\":\"sdk\",\"imsi\":\"310260000000000\",\"channel\":\"1004n\",\"anony\":\"@anonymous:_000000000000000\"}}";*/
							"\"},\"header\":{\"netsubtype\":\"0_\",\"wduserID\":\"\",\"appid\":\"com.koudai.weidian.buyer\",\"mac\":\"58:7f:66:95:f3:32\",\"build\":\"buildnum\",\"islogin\":\"1\",\"serial_no\":\"P4M7N15326043570\",\"network\":\"WIFI\",\"version\":\"2.2.0\",\"android_id\":\"28f524c904ec7b2e\",\"kduss\":\"c11bb0e7eb751884679521a173d8777c\",\"gender\":\"2\",\"wduss\":\"\",\"user_id\":\"475118685\",\"dpi\":\"480\",\"apiv\":\"45\",\"platform\":\"android\",\"os\":\"19\",\"wifiID\":\"\",\"imei\":\"865166028760026\",\"loc\":\"\",\"sessionid\":\"ks_3_1436614344360_917351\",\"appstatus\":\"active\",\"h\":\"1812\",\"w\":\"1080\",\"guid\":\"1436613930120_8898674\",\"userID\":\"475118685\",\"mStation\":\"mcc=460&mnc=1&lac=17692&cellId=22947\",\"brand\":\"Huawei\",\"mid\":\"HUAWEI_MT7-TL00\",\"imsi\":\"460011510506624\",\"channel\":\"1000n\",\"anony\":\"@anonymous:58:7f:66:95:f3:32_865166028760026\"}}";
		String edata = EncryptString(edata_plain);
		return edata;
	}
	
	private String FormatStrGetProductList(String city, String cateID, String cateName, String subCateID, String subCateName, String page)
	{
		String edata_plain = "{\"body\":{\"limit\":\"20\",\"reqID\":\"SEARCH_cat_city_"+subCateName+"\",\"category_id\":\""+subCateID+"\",\"lng\":\"120.404428\",\"city\":\""+city+"\",\"key_word\":\""+subCateName+"\",\"productSource\":\"w2c\",\"flag\":\"cat\",\"order\":\"desc\",\"page\":\""+page+"\",\"sort_key\":\"default\",\"nearby\":\"0\",\"district\":\"\",\"option\":\"1\",\"lat\":\"31.226557\"},\"header\":{\"netsubtype\":\"0_\",\"wduserID\":\"\",\"appid\":\"com.koudai.weidian.buyer\",\"mac\":\"58:7f:66:95:f3:32\",\"build\":\"buildnum\",\"islogin\":\"1\",\"serial_no\":\"P4M7N15326043570\",\"network\":\"WIFI\",\"version\":\"2.2.0\",\"android_id\":\"28f524c904ec7b2e\",\"kduss\":\"c11bb0e7eb751884679521a173d8777c\",\"gender\":\"2\",\"wduss\":\"\",\"user_id\":\"475118685\",\"dpi\":\"480\",\"apiv\":\"45\",\"platform\":\"android\",\"os\":\"19\",\"wifiID\":\"\",\"imei\":\"865166028760026\",\"loc\":\"120.404428,31.226557,0.0\",\"sessionid\":\"ks_3_1436618108865_665366\",\"appstatus\":\"active\",\"h\":\"1812\",\"w\":\"1080\",\"guid\":\"1436613930120_8898674\",\"userID\":\"475118685\",\"mStation\":\"mcc=460&mnc=1&lac=10000&cellId=10000\",\"brand\":\"Huawei\",\"mid\":\"HUAWEI_MT7-TL00\",\"imsi\":\"460011510506624\",\"channel\":\"1000n\",\"anony\":\"@anonymous:58:7f:66:95:f3:32_865166028760026\"}}";
							/*"{\"body\":{\"limit\":\"20\",\"key_word\":\"" +
							subCateName + 
							"\",\"productSource\":\"w2c\",\"flag\":\"cat\",\"order\":\"desc\",\"page\":\"" +
							page + 
							"\",\"reqID\":\"SEARCH_cat_city_" +
							subCateName + 
							"\",\"sort_key\":\"default\",\"category_id\":\"" + 
							subCateID + 
							"\",\"nearby\":\"0\",\"district\":\"\",\"option\":\"1\",\"city\":\"" +
							city + 
							"\"},\"header\":{\"netsubtype\":\"3_UMTS\",\"wduserID\":\"\",\"appid\":\"com.koudai.weidian.buyer\",\"mac\":\"\",\"build\":\"20150210113510\",\"islogin\":\"1\",\"serial_no\":\"unknown\",\"network\":\"3G\",\"version\":\"1.8.0\",\"android_id\":\"549ad29c7d2ce3b0\",\"kduss\":\"cf5200247abb66e22232c16e11a1e8b8\",\"gender\":\"2\",\"wduss\":\"\",\"user_id\":\"346264345\",\"dpi\":\"320\",\"apiv\":\"39\",\"platform\":\"android\",\"os\":\"17\",\"wifiID\":\"\",\"imei\":\"000000000000000\",\"loc\":\"\",\"sessionid\":\"1426166253426_756085\",\"appstatus\":\"active\",\"h\":\"1184\",\"w\":\"720\",\"guid\":\"1423993007870_7189536\",\"userID\":\"346264345\",\"mStation\":\"mcc=310&mnc=260&lac=10000&cellId=10000\",\"brand\":\"generic\",\"mid\":\"sdk\",\"imsi\":\"310260000000000\",\"channel\":\"1004n\",\"anony\":\"@anonymous:_000000000000000\"}}";*/
				
		
		String edata = EncryptString(edata_plain);
		return edata;
	}
	
	private String FormatStrGetProductDetail(String productID, String subCateName)
	{
		String edata_plain = "{\"body\":{\"product_id\":\""+productID+"\",\"productSource\":\"w2c\",\"reqID\":\"SEARCH_cat_city_"+subCateName+"\"},\"header\":{\"netsubtype\":\"0_\",\"wduserID\":\"\",\"appid\":\"com.koudai.weidian.buyer\",\"mac\":\"58:7f:66:95:f3:32\",\"build\":\"buildnum\",\"islogin\":\"1\",\"serial_no\":\"P4M7N15326043570\",\"network\":\"WIFI\",\"version\":\"2.2.0\",\"android_id\":\"28f524c904ec7b2e\",\"kduss\":\"c11bb0e7eb751884679521a173d8777c\",\"gender\":\"2\",\"wduss\":\"\",\"user_id\":\"475118685\",\"dpi\":\"480\",\"apiv\":\"45\",\"platform\":\"android\",\"os\":\"19\",\"wifiID\":\"\",\"imei\":\"865166028760026\",\"loc\":\"120.404428,31.226557,0.0\",\"sessionid\":\"ks_3_1436618443467_960610\",\"appstatus\":\"active\",\"h\":\"1812\",\"w\":\"1080\",\"guid\":\"1436613930120_8898674\",\"userID\":\"475118685\",\"mStation\":\"mcc=460&mnc=1&lac=10000&cellId=10000\",\"brand\":\"Huawei\",\"mid\":\"HUAWEI_MT7-TL00\",\"imsi\":\"460011510506624\",\"channel\":\"1000n\",\"anony\":\"@anonymous:58:7f:66:95:f3:32_865166028760026\"}}"; 
							/*"{\"body\":{\"id\":\"" +
							productID +
							"\",\"reqID\":\"SEARCH_cat_" +
							subCateName +
							"\",\"productSource\":\"w2c\"},\"header\":{\"netsubtype\":\"3_UMTS\",\"wduserID\":\"\",\"appid\":\"com.koudai.weidian.buyer\",\"mac\":\"\",\"build\":\"20150210113510\",\"islogin\":\"1\",\"serial_no\":\"unknown\",\"network\":\"3G\",\"version\":\"1.8.0\",\"android_id\":\"549ad29c7d2ce3b0\",\"kduss\":\"cf5200247abb66e22232c16e11a1e8b8\",\"gender\":\"2\",\"wduss\":\"\",\"user_id\":\"346264345\",\"dpi\":\"320\",\"apiv\":\"39\",\"platform\":\"android\",\"os\":\"17\",\"wifiID\":\"\",\"imei\":\"000000000000000\",\"loc\":\"\",\"sessionid\":\"1426081220384_468078\",\"appstatus\":\"active\",\"h\":\"1184\",\"w\":\"720\",\"guid\":\"1423993007870_7189536\",\"userID\":\"346264345\",\"mStation\":\"mcc=310&mnc=260&lac=10000&cellId=10000\",\"brand\":\"generic\",\"mid\":\"sdk\",\"imsi\":\"310260000000000\",\"channel\":\"1004n\",\"anony\":\"@anonymous:_000000000000000\"}}";*/
				
		
		Log.i("GetProStr", edata_plain);
		String edata = EncryptString(edata_plain);
		return edata;
	}
	
	private String EncryptString(String plainText)
	{
		byte[] encry = SafeUtil.encryptRequestData(
				this.getApplicationContext(), plainText.getBytes(), "1.8.0");
		String str_encry = SafeUtil.Convert(encry);
		try {

			str_encry = URLEncoder.encode(str_encry, "utf-8");
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e1){
			e1.printStackTrace();
		}
		return str_encry;
	}

	public String HttpPostData(String path, String edata) {
		String strResult = "doPostError";
		try {
			HttpClient httpclient = new DefaultHttpClient();//"http://api.buyer.vdian.com"
			String uri = "http://125.39.222.147" + path;
					//	+ "/getItemInfo_v2.do"; // 获取某个手提包的详细页
					//	+ "/proxy_searchItems.do"; // 获取子类商品列表 
					//	+ "/listSearchCategories.do"; // 获取某个大类的子类
					//+ "listShoppingCart.do";
			HttpPost httppost = new HttpPost(uri);
			// 娣诲姞http澶翠俊鎭�			httppost.addHeader("Content-Encoding", "geili-zip");
			httppost.addHeader("Accept-Encoding", "geili-zip");
			httppost.addHeader("Accept-Language", "en-us");
			httppost.addHeader("encryType", "0");
			httppost.addHeader("gzipType", "0");
			httppost.addHeader("Accept", "*/*");
			httppost.addHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=UTF-8");
			httppost.addHeader("User-Agent",
					"Dalvik/1.6.0 (Linux; U; Android 4.2.2; sdk Build/JB_MR1.1) ");
			// httppost.addHeader("Host","api.buyer.vdian.com");
			httppost.addHeader("Connection", "Keep-Alive");

		
			// JSONObject obj = new JSONObject();
			// obj.put("name", "your");
			// obj.put("parentId", "yourparentid");
			// httppost.setEntity(new StringEntity(obj.toString()));
			// String
			// body="apiv=32&edata=w4xeRP9vuZa27RHQ7zvti5lnHJE5OlhAnAHnslIyTQPuTqgpd33ulI%2B19QetksAt5VNbO0F%2BEyDOAepRzYC%2FdgL76MZshJmRJqblCK7N%2F8noh0qVMuIpJXElRyKdERcNcMgw5f%2F37waeIbVfD7THWBYFWT50XeMyT7Mx8FKleG9Q%2ByR%2FbI61hpKG0X1s5yqdnToemPDGvEFpJV3KPsvR8SzE38943YB006GVPmduvR9CgRfnguVF4itQHqVUcVOqIK87UhbEkjsTUdBW7vmD%2BSFup4DeXG%2FOm7RZRYEi4AFAAVa1lJ%2BqQ8Tz7coB7kuoAeL4W69BRZ4NUdSfiDgBdQMfUaOqGHlofKqPpyoJD1a5SPHs4WXJn9YrK0Pywh3uNikw4gXsvMy1NOM4UYvDIqzTLBAzMuPGldqCvmryyVPwmpNni4zkEOgQWmBBW8HVEyWtm4cU4kh%2BshoJgWHonQFZPYHadZcnYEIXeXPlLhQp4G93DjO6L7I%2FCns3hiobZxIb2fDDvcpSsyCF9Hr87tgih%2BFxGGVkUjZi7%2Bn8svuSWUqGUw0OON%2FzbbuNILgZAzTKdN030suEiO6JHJnJGJjTkS%2BC8BS7HYaNg3haE841w7qGBfOfQqDV3rgOwMXUr0YcbRf5YJSUagf8KV1AJ5hpIVroJ3MzhtZxsoIh5Uw458K5mdnVEPXyVNTieQrN7T7XKFJmrVoZGRwExMKvmYoeyNookAypw99K6u7RCCzZ91IOiy%2BvT6FGL8AW5CLs0cKB%2FR6mYgTjQuOyfS4DL%2Bi5gADJcR2O2VOMxxWU0IkNvVRcRIvPF18BRgNmeWJksmQFInROS0JbnnUxaVyB5bBQNGr1yz4gBGJMdANcRZ0HGu64fqLhnGLcdoTBjWI%2FRDEMsOoD9obj4A7JYM0Dkv3woCUzAGF%2BgnhfCjRxiPRtNiabYwPXYDrrpHxJUHA6&platform=android&encryType=1&crc=bd62df1aab2f2ec68e322b69bfae6850&gzipTapiv=32&edata=w4xeRP9vuZa27RHQ7zvti5lnHJE5OlhAnAHnslIyTQPuTqgpd33ulI%2B19QetksAt5VNbO0F%2BEyDOAepRzYC%2FdgL76MZshJmRJqblCK7N%2F8noh0qVMuIpJXElRyKdERcNcMgw5f%2F37waeIbVfD7THWBYFWT50XeMyT7Mx8FKleG9Q%2ByR%2FbI61hpKG0X1s5yqdnToemPDGvEFpJV3KPsvR8SzE38943YB006GVPmduvR9CgRfnguVF4itQHqVUcVOqIK87UhbEkjsTUdBW7vmD%2BSFup4DeXG%2FOm7RZRYEi4AFAAVa1lJ%2BqQ8Tz7coB7kuoAeL4W69BRZ4NUdSfiDgBdQMfUaOqGHlofKqPpyoJD1a5SPHs4WXJn9YrK0Pywh3uNikw4gXsvMy1NOM4UYvDIqzTLBAzMuPGldqCvmryyVPwmpNni4zkEOgQWmBBW8HVEyWtm4cU4kh%2BshoJgWHonQFZPYHadZcnYEIXeXPlLhQp4G93DjO6L7I%2FCns3hiobZxIb2fDDvcpSsyCF9Hr87tgih%2BFxGGVkUjZi7%2Bn8svuSWUqGUw0OON%2FzbbuNILgZAzTKdN030suEiO6JHJnJGJjTkS%2BC8BS7HYaNg3haE841w7qGBfOfQqDV3rgOwMXUr0YcbRf5YJSUagf8KV1AJ5hpIVroJ3MzhtZxsoIh5Uw458K5mdnVEPXyVNTieQrN7T7XKFJmrVoZGRwExMKvmYoeyNookAypw99K6u7RCCzZ91IOiy%2BvT6FGL8AW5CLs0cKB%2FR6mYgTjQuOyfS4DL%2Bi5gADJcR2O2VOMxxWU0IkNvVRcRIvPF18BRgNmeWJksmQFInROS0JbnnUxaVyB5bBQNGr1yz4gBGJMdANcRZ0HGu64fqLhnGLcdoTBjWI%2FRDEMsOoD9obj4A7JYM0Dkv3woCUzAGF%2BgnhfCjRxiPRtNiabYwPXYDrrpHxJUHA6&platform=android&encryType=1&crc=bd62df1aab2f2ec68e322b69bfae6850&gzipType=0&kid=1.8.0";
			// String
			// body="apiv=32&edata=72pICm%2BhnI1mwy%2BFV4fSflncIBJc1OXicDjl0tOmAzpyeiIAal6Ba4Xc2n0o14kUf%2FLpUhLrROAkKZI4b0udC1qZXBfVcTp%2BDy1T3Qe1bcK4%2FbZkDzL0tUOlPpvOteagMmzXNqXOYuB4Rvij8cFenqy7iuvzII5SPXJw5Ox%2B%2FTGy4ZI3RllwL8eqO95Y19WTA2LguzcEqMGHypafrVxKL8ENnYy0u7spyGGcbXNMeR%2FAQaAzFjl%2F0XpVJ4cmqgREPSulvi%2Fu4prHTNzYLpkCLHvwn4UXcqyPCh5R9vx2S%2FUO5TzTpTYIwdmaWXkn4hIe4AQbO%2Bfy61%2B3EL1EcF%2FuEWGRZxgZOqdzLLNy4%2BBZILd%2FB%2BpJww9NOppAWDRP6zu%2FgqZII6G9mKAGAK%2Fkdc%2BKLd%2BWM2A3DK6PRQo7x68zL8l%2B8vkLRWzimCQ6V9AfPKo%2BvIoLgkNnVLfvGsG7N2tg0GRX2mafI4tdGihTJYjP%2FHUcfcKQyoS3sjecVaIKd%2FQmqT9ZdzHoX754G7cEpvrCetY5lJZbI9btEzRP%2FPn0u5YufaFiAuxdK1%2BYtdUONkY7SgIYkGsjNVv%2FuJZZ0KUXqengqH8qcM%2Bj%2BvPWkFEEiBwrNayVqe7QAJCZTyM2RaLdZ2wY%2B8pEDUpXsC0BtIEZSP8%2BUPmysSFgqQuemFi%2BcYQm7eh1rMn2p2NVKwj%2FN53C7ZhZutG3ob%2BhM5swX9dxT%2B5DRPlgVyI%2FcvOzCKE1eESdqxHWgoAgBtP0wj2K2AVGHEOO56CUBN69UEN%2FB6e7qZveLcY161k9o6J%2FDfgw5YDoPaK4qAAwE66FuDt5HEFst90FSxP23lJMOCHdUHHo3dVUieZLZujFCd93jTumm46foert7QbKUik%2BYAw3xuygDh30ccMZ9kxCvHy%2FsD3H%2FM6FGLlYkuInxtHJoTxaibBOlL9xKPOS1TVweDF4QLE%2FB1GwAVvFBmGeXnCZQYtket6QPD8XmkwQV%2B5i4dYoYhiA5Y2X1hTSLgJmrPS74q7sDtysA5BPeIVl2Z3OCQWMQzrNqLpCx8r2MGqMRWKivofCdUpINtL8rFN5ByvTytXt9yCO1bmwy10UZmHjM4Kqlv0Wz51tujLCNFyYFo5wF7M%3D&platform=android&encryType=1&crc=1041402d55bb89d6e6762611370d1e8b&gzipType=0&kid=1.8.0";
			String body = "apiv=45&edata="//"apiv=32&edata=" 
					+ edata
					//"w4xeRP9vuZa27RHQ7zvti5lnHJE5OlhAnAHnslIyTQPuTqgpd33ulI%2B19QetksAt5VNbO0F%2BEyDOAepRzYC%2FdgL76MZshJmRJqblCK7N%2F8noh0qVMuIpJXElRyKdERcNcMgw5f%2F37waeIbVfD7THWBYFWT50XeMyT7Mx8FKleG9Q%2ByR%2FbI61hpKG0X1s5yqdnToemPDGvEFpJV3KPsvR8SzE38943YB006GVPmduvR9CgRfnguVF4itQHqVUcVOqIK87UhbEkjsTUdBW7vmD%2BSFup4DeXG%2FOm7RZRYEi4AErhb0euyJ11uazSwFnKa1s2xPEQr0tbYixsqwRcNMlY9Iw4n88o1vY7w7YU1xjd3QrF9aMnLxp%2FXv4e5KkB6zLNikw4gXsvMy1NOM4UYvDIqzTLBAzMuPGldqCvmryyVPwmpNni4zkEOgQWmBBW8HVEyWtm4cU4kh%2BshoJgWHonQFZPYHadZcnYEIXeXPlLhQp4G93DjO6L7I%2FCns3hiobZxIb2fDDvcpSsyCF9Hr87tgih%2BFxGGVkUjZi7%2Bn8svskyp%2Faz%2FGPuoBfE5hCrnzxhlL1QR0pdjS2%2F9nm5Q4DWpjTkS%2BC8BS7HYaNg3haE841w7qGBfOfQqDV3rgOwMXUr0YcbRf5YJSUagf8KV1AJ2JKKXI%2BuNjQO2OQi5DhGc5zwGlwkILRbk9PFstNFnzRbm2tj56%2F73KPdPnuuj%2BBmIoeyNookAypw99K6u7RCCzZ91IOiy%2BvT6FGL8AW5CLs0cKB%2FR6mYgTjQuOyfS4DL%2Bi5gADJcR2O2VOMxxWU0IkNvVRcRIvPF18BRgNmeWJksmQFInROS0JbnnUxaVyB5bBQNGr1yz4gBGJMdANcRZ0HGu64fqLhnGLcdoTBjWI%2FRDEMsOoD9obj4A7JYM0Dkv3woCUzAGF%2BgnhfCjRxiPRtNiabYwPXYDrrpHxJUHA6" +
					+ "&platform=android&encryType=1&crc=f96fe697fa806a5e2afacf9c7a106c28&gzipType=0&kid=1.8.0";
			httppost.setEntity(new StringEntity(body));

			HttpResponse response;
			response = httpclient.execute(httppost);

			// 妫�獙鐘舵�鐮侊紝濡傛灉鎴愬姛鎺ユ敹鏁版嵁
			int code = response.getStatusLine().getStatusCode();
			Log.i("status code", Integer.toString(code));
			if (code == 200) {
				HttpEntity entity = response.getEntity();
				
				int bNeedDecrypt = 0;
				if(bNeedDecrypt == 1)
				{
					byte[] data;
					//byte[] convert_data;
					byte[] dencry;
					// String rev = EntityUtils.toString(response.getEntity());// 杩斿洖	
					
					// int length=(int) entity.getContentLength();
					int length = 4096;
					ByteArrayBuffer buffer = new ByteArrayBuffer(length);
					InputStream is = entity.getContent();
	
					byte[] tmp = new byte[4096];
					while (true) {
						int len = is.read(tmp);
						if (len == -1)
							break;
						buffer.append(tmp, 0, len);
					}
					is.close();
	
					data = buffer.toByteArray();
	
					//瑙ｅ瘑鏃剁涓変釜鍙傛暟瑕佺湅鍙戦�鏁版嵁鍖呬腑鐨刱id鍊�		
					dencry = "".getBytes();
					String final_dec = "";
					try{
						dencry = SafeUtil.decryptRequestData(null, data, "1.8.0");
						final_dec = new String(gzipdec(dencry));
						Log.i("final_dec", final_dec);
					}catch(Exception e)
					{
						e.printStackTrace();
						try
						{
							dencry = SafeUtil.decryptRequestData(null, data, "3.0.1");
							final_dec = new String(gzipdec(dencry));
							Log.i("final_dec", final_dec);
						}catch(Exception e1)
						{
							e1.printStackTrace();
						}
					}
					//Log.i("response", data);
					//瑙ｅ瘑瀹屼箣鍚庤繕瑕佽В鍘嬬缉
					
					//writeData("final_dec");
					//writeData(final_dec);
					//Thread.sleep(100);
					return final_dec;
				}else
				{
					int length=(int) entity.getContentLength();
					strResult = EntityUtils.toString(entity);
					return strResult;
				}
			}
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
			strResult = e.getMessage().toString();
			e.printStackTrace();
		} catch (Exception e) {
			strResult = e.getMessage().toString();
			e.printStackTrace();
		}
		return "";
	}

	public static byte[] gzipdec(byte[] arg6) {
	        byte[] v0_3;
	        if(arg6 != null && arg6.length != 0) {
	            ByteArrayOutputStream v1 = new ByteArrayOutputStream();
	            ByteArrayInputStream v2 = new ByteArrayInputStream(arg6);
	            try {
	                GZIPInputStream v0_2 = new GZIPInputStream(((InputStream)v2));
	                byte[] v3 = new byte[256];
	                while(true) {
	                    int v4 = v0_2.read(v3);
	                    if(v4 < 0) {
	                        break;
	                    }
	                    v1.write(v3, 0, v4);
	                }

	                v0_3 = v1.toByteArray();
	                v1.close();
	                return v0_3;
	            
	        }
	            catch (Exception e) {
	    			String strResult = e.getMessage().toString();
	    			e.printStackTrace();
	    		}
	    }
	        return null;
}
}
