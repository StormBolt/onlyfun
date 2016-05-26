package com.onlyfun.learn.action;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Created by dujiacheng on 2016/5/4.
 */
public abstract class AbstractAction {
    Logger logger = Logger.getLogger(AbstractAction.class);

    /**
     * 写ajax响应
     *
     *
     * @param resp
     * @param content
     * @param cacheTime
     * @param jsoncallback
     */
    public void writeResponse(HttpServletResponse resp, String content, long cacheTime, String jsoncallback) {
        PrintWriter writer = null;

        try {
            resp.setHeader("P3P", "CP=CAO PSA OUR");

            if (StringUtils.isNotBlank(jsoncallback)) {
                resp.setCharacterEncoding("UTF-8");
                content = StringEscapeUtils.escapeHtml(jsoncallback) + "(" + content + ")";
            } else {
                resp.setCharacterEncoding("utf8");
            }

            resp.setContentType("text/plain");
            setCacheHeader(resp, cacheTime);
            writer = resp.getWriter();
            writer.write(content);
        } catch (IOException e) {
            logger.error("writeResponse error:", e);
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    /**
     * 设置浏览器输出头
     *
     * @param resp
     * @param cacheTime
     */
    public static void setCacheHeader(HttpServletResponse resp, long cacheTime) {
        if (cacheTime <= 0) {
            setNoCacheHeader(resp);

            return;
        }

        long now = new Date().getTime();

        resp.setDateHeader("Expires", now + cacheTime);
        resp.setDateHeader("Last-Modified", now);
        resp.setHeader("Cache-Control", "max-age=" + (cacheTime / 1000));
    }

    /**
     * Method description：
     *
     *
     * @param res
     *
     * @date 16/05/04
     * @autor dujiacheng
     */
    public static void setNoCacheHeader(HttpServletResponse res) {
        res.setHeader("Pragma", "No-Cache");
        res.setHeader("Cache-Control", "no-cache, no-store");
        res.setDateHeader("Expires", 0);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
