package com.xiazhenyu.demo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiazhenyu.bean.Card;
import com.xiazhenyu.bean.ResultVO;
import com.xiazhenyu.bean.User;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;

/**
 * Date: 2023/12/13
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class GraphQLSDLDemo {


    public static void main(String[] args) throws IOException {
        //读取graphqls文件
        String fileName = "user.graphqls";
//        String fileContent = IOUtils.toString(GraphQLSDLDemo.class.getClassLoader().getResource(fileName), "UTF-8");
        //解析文件
//        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(fileContent);
        String schema = "schema {\n"
                + "    query: UserQuery\n"
                + "}\n"
                + "\n"
                + "type UserQuery {\n"
                + "    queryUser : ResultVO\n"
                + "    ## queryUser：类似于java中方法名\n"
                + "}\n"
                + "\n"
                + "type ResultVO {\n"
                + "    item: [User]\n"
                + "    total: Int\n"
                + "}\n"
                + "\n"
                + "type User {\n"
                + "    id: ID!\n"
                + "    name:String\n"
                + "    age:Int\n"
                + "    card:Card\n"
                + "}\n"
                + "type Card {\n"
                + "    cardNumber:String\n"
                + "    userId:ID\n"
                + "}\n";
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);

        System.out.println(typeDefinitionRegistry);

        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring().type("UserQuery", builder -> builder.dataFetcher("queryUser", environment -> {
            //解析请求参数，根据业务返回结果
//            String id = environment.getArgument("id");
            final String jsonString = JSON.toJSONString(environment.getArguments());
            System.out.println(jsonString);
            ResultVO resultVO = new ResultVO();
            resultVO.setTotal(10);
            List<User> userList = new ArrayList<>();
            List<Card> cardList=new ArrayList<>();

            Card card = new Card("123456", 1L);
            cardList.add(card);
            final User user1 = new User(18, 1L, "user0" + 1, card);
            final User user2 = new User(18, 1L, "user0" + 1, card);
            userList.add(user1);
            userList.add(user2);
            resultVO.setItem(userList);
            return resultVO;
        })).build();

        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);

        GraphQL graphQL = GraphQL.newGraphQL(graphQLSchema).build();

        String query = "query queryUser{queryUser{item{id,name,card{cardNumber,userId}},total}}";
        ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query).build();
        ExecutionResult result = graphQL.execute(executionInput);

        System.out.println("query: " + query);
        System.out.println(result.toSpecification());
    }

}