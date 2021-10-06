package com.mooc.meetingfilm.hystrix.command;

import com.netflix.hystrix.*;
import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @date: 2021/9/5 9:02
 * @ClassName: CommandCollapser
 * @description:请求合并处理对象
 */
@Data
public class CommandCollapser extends HystrixCollapser<List<String>, String, Integer> {

    private Integer id;

    public CommandCollapser(Integer id){
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("CommandCollapser"))
                .andCollapserPropertiesDefaults(
                        HystrixCollapserProperties.defaultSetter().withTimerDelayInMilliseconds(1000)
                        )
        );
        this.id = id;
    }

    /**
     * 获取请求对象
     * @return
     */
    @Override
    public Integer getRequestArgument() {
        return id;
    }

    /**
     * 批量业务处理
     * @param collapsedRequests
     * @return
     */
    @Override
    protected HystrixCommand<List<String>> createCommand(Collection<CollapsedRequest<String, Integer>> collapsedRequests) {
        return new BatchCommand(collapsedRequests);
    }

    /**
     * 批量处理结果与请求业务间映射关系处理
     * @param batchResponse
     * @param collapsedRequests
     */
    @Override
    protected void mapResponseToRequests(List<String> batchResponse, Collection<CollapsedRequest<String, Integer>> collapsedRequests) {
        int counts = 0;
        Iterator<HystrixCollapser.CollapsedRequest<String, Integer>> iterator = collapsedRequests.iterator();
        while (iterator.hasNext()) {
            HystrixCollapser.CollapsedRequest<String, Integer> response = iterator.next();
            String  result = batchResponse.get(counts++);

            response.setResponse(result);
        }
    }
}


@Data
class BatchCommand extends  HystrixCommand<List<String>>{

    private Collection<HystrixCollapser.CollapsedRequest<String, Integer>> collection;

    protected BatchCommand(Collection<HystrixCollapser.CollapsedRequest<String, Integer>> collection) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("BatchCommand")));
        this.collection = collection;
    }

    @Override
    protected List<String> run() throws Exception {
        System.out.println("currentThread: "+Thread.currentThread().getName());

        ArrayList<String> result = Lists.newArrayList();

        Iterator<HystrixCollapser.CollapsedRequest<String, Integer>> iterator = collection.iterator();
        while (iterator.hasNext()) {
            HystrixCollapser.CollapsedRequest<String, Integer> request = iterator.next();
            Integer reqParam = request.getArgument();

            // 具体业务逻辑处理
            result.add("Mooc req: "+reqParam);
        }

        return result;
    }
}

















