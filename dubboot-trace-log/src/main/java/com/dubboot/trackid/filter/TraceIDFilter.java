/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dubboot.trackid.filter;

import org.slf4j.MDC;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.dubboot.trackid.utils.TraceIDUtils;

/**
 * TokenInvokerFilter
 *
 * @author william.liangf
 */
@Activate()
public class TraceIDFilter implements Filter {
	private static final String TRACE_ID="TRACE_ID";
    public Result invoke(Invoker<?> invoker, Invocation inv)
            throws RpcException {
    	if(inv.getAttachment(TRACE_ID)!=null){
    		TraceIDUtils.setTraceId(inv.getAttachment(TRACE_ID));
    		String mdcData = String.format("[TraceID:%s]", inv.getAttachment(TRACE_ID));
            MDC.put("mdcData", mdcData);
    	}else if(TraceIDUtils.getTraceId()!=null){
    		inv.getAttachments().put("TRACE_ID", TraceIDUtils.getTraceId());
    	}
        return invoker.invoke(inv);
    }
}