package com.xiazhenyu.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.language.Document;
import graphql.language.FieldDefinition;
import graphql.language.ListType;
import graphql.language.NonNullType;
import graphql.language.ObjectTypeDefinition;
import graphql.language.OperationTypeDefinition;
import graphql.language.SchemaDefinition;
import graphql.language.TypeName;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Date: 2023/12/13
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class GraphQLSDLDemo1 {

    public static void main(String[] args) throws IOException {

        String QUERY_SCHEMA = "query queryByParam{queryByParam";

        String TOKEN_CLOSE_BRACE="}";

        SchemaDefinition schemaDefinition = SchemaDefinition.newSchemaDefinition()
                .operationTypeDefinition(OperationTypeDefinition.newOperationTypeDefinition()
                        .name("query")
                        .typeName(TypeName.newTypeName()
                                .name("QueryByParam")
                                .build())
                        .build())
                .build();
        ObjectTypeDefinition objectTypeDefinition = ObjectTypeDefinition.newObjectTypeDefinition()
                .name("QueryByParam")
                .fieldDefinition(FieldDefinition.newFieldDefinition()
                        .name("queryByParam")
                        .type(TypeName.newTypeName()
                                .name("Arg0")
                                .build())
                        .build())
                .build();
        ObjectTypeDefinition userObjectTypeDefinition = ObjectTypeDefinition.newObjectTypeDefinition()
                .name("Arg0_0")
                .fieldDefinition(FieldDefinition.newFieldDefinition().name("id").type(NonNullType.newNonNullType().build()).type(TypeName.newTypeName().name("ID").build()).build())
                .fieldDefinition(FieldDefinition.newFieldDefinition().name("name").type(TypeName.newTypeName().name("String").build()).build())
//                .fieldDefinition(FieldDefinition.newFieldDefinition().name("age").type(TypeName.newTypeName().name("Int").build()).build())
                .fieldDefinition(FieldDefinition.newFieldDefinition().name("cardList").type(ListType.newListType()
                        .type(TypeName.newTypeName().name("Arg0_0_0").build())
                        .build()
                ).build())
                .build();
        ObjectTypeDefinition cardObjectTypeDefinition = ObjectTypeDefinition.newObjectTypeDefinition()
                .name("Arg0_0_0")
                .fieldDefinition(FieldDefinition.newFieldDefinition().name("cardNumber").type(TypeName.newTypeName().name("String").build()).build())
                .fieldDefinition(FieldDefinition.newFieldDefinition().name("userId").type(TypeName.newTypeName().name("ID").build()).build())
                .build();
        ObjectTypeDefinition resultTypeDefinition = ObjectTypeDefinition.newObjectTypeDefinition()
                .name("Arg0")
                .fieldDefinition(FieldDefinition.newFieldDefinition().name("itemList").type(ListType.newListType().type(TypeName.newTypeName()
                        .name("Arg0_0")
                        .build()).build()).build())
                .fieldDefinition(FieldDefinition.newFieldDefinition()
                        .name("total").type(TypeName.newTypeName().name("Int").build())
                        .build())
                .build();
        Document document = Document.newDocument()
                .definition(schemaDefinition)
                .definition(objectTypeDefinition)
                .definition(userObjectTypeDefinition)
                .definition(cardObjectTypeDefinition)
                .definition(resultTypeDefinition)
                .build();

//        String graphqlQuery = "query queryByParam{queryByParam{itemList{id:ID,name:String,cardList{cardNumber:String,userId:Int}},total:Int}}";
//        String graphqlQuery="query queryByParam{queryByParam{item{id:ID,name:String,card{cardNumber:String}},total:Int}}";
//        String graphqlQuery = "query queryByParam{queryByParam{\n"
//                + "    code:String,msg:String,\n"
//                + "    data{\n"
//                + "        records{[brandName:String,goodsNo:String]},\n"
//                + "        total:Int,\n"
//                + "        searchCount:Boolean,\n"
//                + "        size:Int,\n"
//                + "        pages:Int\n"
//                + "        }\n"
//                + "    }\n"
//                + "}";
        String graphqlQuery="{\n"
                + "    code:String,\n"
                + "    msg:String,\n"
                + "    data\n"
                + "    {\n"
                + "        records\n"
                + "        {[\n"
                + "            brandName:String,\n"
                + "            goodsNo:String\n"
                + "        ]},\n"
                + "        total:Int,\n"
                + "        searchCount:Boolean,\n"
                + "        size:Int,\n"
                + "        pages:Int\n"
                + "    }\n"
                + "}\n";
        final Document document1 = GraphQLDocumentFactory.parseGraphQLDocument(QUERY_SCHEMA+graphqlQuery.replaceAll("\\s", "")+TOKEN_CLOSE_BRACE);
//        final Document document1 = GraphQLDocumentFactory.parseGraphQLDocument(graphqlQuery);
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.buildRegistry(document1);

        System.out.println(typeDefinitionRegistry);

        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring().type("QueryByParam", builder -> builder.dataFetcher("queryByParam", environment -> {
            //解析请求参数，根据业务返回结果
//            String id = environment.getArgument("id");
            EpResponseStatus responseStatus = new EpResponseStatus();
            BasicGoodsVO basicGoodsVO = new BasicGoodsVO();
            basicGoodsVO.setBrandId(11L);
            basicGoodsVO.setBrandName("测试品牌1");
            basicGoodsVO.setGoodsNo("ATEST001");
            basicGoodsVO.setDesignNo("ATEST01");
            BasicGoodsVO basicGoodsVO1 = new BasicGoodsVO();
            basicGoodsVO1.setBrandId(22L);
            basicGoodsVO1.setBrandName("测试品牌2");
            basicGoodsVO1.setGoodsNo("ATEST002");
            basicGoodsVO1.setDesignNo("ATEST02");
            List<BasicGoodsVO> basicGoodsVOList = new ArrayList<>();
            basicGoodsVOList.add(basicGoodsVO);
            basicGoodsVOList.add(basicGoodsVO1);
            PageResult pageResult = new PageResult<>();
            pageResult.setSize(10);
            pageResult.setTotal(2);
            pageResult.setPages(1);
            pageResult.setRecords(basicGoodsVOList);
            responseStatus.setData(pageResult);
            return responseStatus;

        })).build();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);

        GraphQL graphQL = GraphQL.newGraphQL(graphQLSchema).build();

//        String query = "query queryByParam{queryByParam{item{id,name,card{cardNumber,userId}},total}}";
//        String query = "query queryByParam{queryByParam{item{id,name,card{cardNumber,userId}},total}}";
        String query = GraphQLDocumentFactory.parseQueryQL(QUERY_SCHEMA+graphqlQuery.replaceAll("\\s","")+TOKEN_CLOSE_BRACE);
//        String query = GraphQLDocumentFactory.parseQueryQL(graphqlQuery);
        ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query).build();
        ExecutionResult result = graphQL.execute(executionInput);

        System.out.println("query: " + QUERY_SCHEMA+graphqlQuery.replaceAll("\\s","")+TOKEN_CLOSE_BRACE);
//        System.out.println("query: " + query);
        System.out.println(result.toSpecification());
        Map<String, String> parseObject = JSON.parseObject(JSON.toJSONString(result.toSpecification().get("data")),
                new TypeReference<Map<String, String>>() {
                });
        final EpResponseStatus responseStatus = JSON.parseObject(parseObject.get("queryByParam"), EpResponseStatus.class);
        System.out.println(responseStatus);
    }


}