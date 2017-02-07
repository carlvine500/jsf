/**
 * Copyright 2004-2048 .
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ipd.jsf.gd.util;

import com.ipd.jsf.gd.error.RpcException;
import com.ipd.jsf.gd.msg.MessageHeader;

/**
 * Title:异常处理工具类<br>
 * <p/>
 * Description: <br>
 * <p/>
 */
public final class ExceptionUtils {

    /**
     * 返回堆栈信息（e.printStackTrace()的内容）
     *
     * @param e
     *         Throwable
     * @return 异常堆栈信息
     */
    public static String toString(Throwable e) {
        StackTraceElement[] traces = e.getStackTrace();
        StringBuilder sb = new StringBuilder(1024);
        sb.append(e.toString()).append("\n");
        if (traces != null) {
            for (StackTraceElement trace : traces) {
                sb.append("\tat ").append(trace).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * 返回消息+简短堆栈信息（e.printStackTrace()的内容）
     *
     * @param e
     *         Throwable
     * @param stackLevel
     *         堆栈层级
     * @return 异常堆栈信息
     */
    public static String toShortString(Throwable e, int stackLevel) {
        StackTraceElement[] traces = e.getStackTrace();
        StringBuilder sb = new StringBuilder(1024);
        sb.append(e.toString()).append("\t");
        if (traces != null) {
            for (int i = 0; i < traces.length; i++) {
                if (i < stackLevel) {
                    sb.append("\tat ").append(traces[i]).append("\t");
                } else {
                    break;
                }
            }
        }
        return sb.toString();
    }

    /**
     * 封装RpcException
     *
     * @param header
     *         消息头
     * @param throwable
     *         异常
     * @return RpcException
     */
    public static RpcException handlerException(MessageHeader header, Throwable throwable){
        RpcException exception = null;
        if(throwable instanceof RpcException){
            exception = (RpcException) throwable;
            if(header != null) exception.setMsgHeader(header);

        }else{
            exception = new RpcException(header,throwable);

        }
        return  exception;
    }
}