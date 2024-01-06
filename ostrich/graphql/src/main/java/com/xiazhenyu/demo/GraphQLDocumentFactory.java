package com.xiazhenyu.demo;

import graphql.language.Document;
import graphql.language.FieldDefinition;
import graphql.language.ListType;
import graphql.language.ObjectTypeDefinition;
import graphql.language.OperationTypeDefinition;
import graphql.language.SchemaDefinition;
import graphql.language.Type;
import graphql.language.TypeName;

/**
 * Date: 2023/12/14
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class GraphQLDocumentFactory {


    public static final String TOKEN_OPEN_BRACE = "{";

    public static final String TOKEN_CLOSE_BRACE = "}";

    public static final String TOKEN_COLON = ":";

    public static final String TOKEN_DOT = ",";

    public static final String TOKEN_OPEN_BRACKET = "[";

    public static final String TOKEN_CLOSE_BRACKET = "]";


    public static void main(String[] args) {
//        parseGraphQLDocument(null);

        System.out.println(parseQueryQL("query queryByParam{queryByParam{item{id:ID,name:String,card{cardNumber:String,userId:Int}},total:Int}}"));
    }

    public static Document parseGraphQLDocument(String graphqlQuery) {

        SchemaDefinition schemaDefinition = SchemaDefinition.newSchemaDefinition()
                .operationTypeDefinition(OperationTypeDefinition.newOperationTypeDefinition().name("query")
                        .typeName(TypeName.newTypeName().name("QueryByParam").build())
                        .build())
                .build();
        ObjectTypeDefinition objectTypeDefinition = ObjectTypeDefinition.newObjectTypeDefinition().name("QueryByParam")
                .fieldDefinition(FieldDefinition.newFieldDefinition().name("queryByParam").type(TypeName.newTypeName().name("Arg0").build())
                        .build())
                .build();

        Document.Builder document = Document.newDocument();
        document.definition(schemaDefinition);
        document.definition(objectTypeDefinition);
        String trueQueryCriteria = graphqlQuery.substring("query queryByParam{queryByParam".length(), graphqlQuery.length() - 1);
        System.out.println(trueQueryCriteria);
        //根节点解析，definitionName为ResultVO
        buildObjectTypeDefinition(trueQueryCriteria, document, "Arg0");
        return document.build();
    }


    private static void buildObjectTypeDefinition(String queryFragment, Document.Builder document, String definitionName) {
        //构建构建对应的ObjectTypeDefinition
        ObjectTypeDefinition.Builder objectTypeDefinition = ObjectTypeDefinition.newObjectTypeDefinition();
        objectTypeDefinition.name(definitionName);
        //排除fragment两端的花括号
        final String fragment = queryFragment.substring(1, queryFragment.length() - 1);
        //fragmentChunk对应的ObjectTypeDefinition的name变化的下标
        int definitionNameIndex = 0;
        //需要判断fragment中是否含有花括号，有，则说明有需要进一步解析的fragment
        if (fragment.contains(TOKEN_CLOSE_BRACE)) {
            StringBuilder objectTypeDefinitionChunk = new StringBuilder();
            final char[] fragmentCharArray = fragment.toCharArray();
            boolean flag = false;
            int brace_counter = 0;
            for (int i = 0; i < fragmentCharArray.length; i++) {
                if (TOKEN_DOT.equals(String.valueOf(fragmentCharArray[i])) && !flag) {
                    //以逗号进行分割，得到每一个FieldDefinition节点
                    objectTypeDefinition.fieldDefinition(FieldDefinition.newFieldDefinition()
                            .name(objectTypeDefinitionChunk.toString().split(TOKEN_COLON)[0])
                            .type(TypeName.newTypeName().name(objectTypeDefinitionChunk.toString().split(TOKEN_COLON)[1]).build())
                            .build());
                    objectTypeDefinitionChunk = new StringBuilder();
                    continue;
                }
                objectTypeDefinitionChunk.append(fragmentCharArray[i]);
                //匹配到左花括号,计数器加1
                if (TOKEN_OPEN_BRACE.equals(String.valueOf(fragmentCharArray[i]))) {
                    brace_counter++;
                    flag = true;
                }
                //匹配到右花括号，计数器减1
                if (TOKEN_CLOSE_BRACE.equals(String.valueOf(fragmentCharArray[i]))) {
                    brace_counter--;
                }
                //匹配到一个fragmentChunk
                if (flag && brace_counter == 0) {
//                    objectTypeDefinitionChunk
                    Type type;
                    String name;
                    boolean isCollection;
                    String subQueryFragment;
                    if ("[".equals(objectTypeDefinitionChunk.substring(objectTypeDefinitionChunk.indexOf("{")+1, objectTypeDefinitionChunk.indexOf("{") + 2))) {
                        name = objectTypeDefinitionChunk.substring(0, objectTypeDefinitionChunk.toString().indexOf("{["));
                        isCollection = true;
                        objectTypeDefinitionChunk.replace(objectTypeDefinitionChunk.indexOf("{["), objectTypeDefinitionChunk.indexOf("{[") + 2, "{");
                        objectTypeDefinitionChunk.replace(objectTypeDefinitionChunk.lastIndexOf("]}"), objectTypeDefinitionChunk.lastIndexOf("]}") + 2, "}");
                        subQueryFragment = objectTypeDefinitionChunk.substring(name.length());
                    } else {
                        name = objectTypeDefinitionChunk.substring(0, objectTypeDefinitionChunk.toString().indexOf("{"));
                        isCollection = false;
                        subQueryFragment = objectTypeDefinitionChunk.substring(name.length());
                    }

                    String typeName = definitionName + "_" + definitionNameIndex;
                    if (isCollection) {
                        type = ListType.newListType().type(TypeName.newTypeName().name(typeName).build()).build();
                    } else {
                        type = TypeName.newTypeName().name(typeName).build();
                    }
                    objectTypeDefinition.fieldDefinition(FieldDefinition.newFieldDefinition().name(name).type(type).build());
                    buildObjectTypeDefinition(subQueryFragment, document, typeName);
                    definitionNameIndex++;
                    objectTypeDefinitionChunk = new StringBuilder();
                    flag = false;
                    //跳过逗号
                    i++;
                }
                if (i == fragmentCharArray.length - 1) {
                    objectTypeDefinition.fieldDefinition(FieldDefinition.newFieldDefinition()
                            .name(objectTypeDefinitionChunk.toString().split(TOKEN_COLON)[0])
                            .type(TypeName.newTypeName().name(objectTypeDefinitionChunk.toString().split(TOKEN_COLON)[1]).build())
                            .build());
                    break;
                }
            }
            //将上一步构建的ObjectTypeDefinition放入Document中
            document.definition(objectTypeDefinition.build());

        } else {
            //以逗号进行分割，得到每一个FieldDefinition节点
            for (String fieldDefinitionFragment : fragment.split(TOKEN_DOT)) {
                objectTypeDefinition.fieldDefinition(FieldDefinition.newFieldDefinition()
                        .name(fieldDefinitionFragment.split(TOKEN_COLON)[0])
                        .type(TypeName.newTypeName().name(fieldDefinitionFragment.split(TOKEN_COLON)[1]).build())
                        .build());
            }
            //将上一步构建的ObjectTypeDefinition放入Document中
            document.definition(objectTypeDefinition.build());
        }
    }


    /**
     * 从前端传过来的查询条件中，解析出真正的符合GraphQL规范的查询语句
     * @param queryFragment 查询片段
     * @return graphQL查询语句
     */
    public static String parseQueryQL(String queryFragment) {
        StringBuilder graphQLQuery = new StringBuilder();
        final char[] fragmentCharArray = queryFragment.toCharArray();
        boolean filterFlag = false;
        for (int i = 0; i < fragmentCharArray.length; i++) {
            if (TOKEN_OPEN_BRACKET.equals(String.valueOf(fragmentCharArray[i])) || TOKEN_CLOSE_BRACKET.equals(String.valueOf(fragmentCharArray[i]))) {
                continue;
            }
            if (TOKEN_COLON.equals(String.valueOf(fragmentCharArray[i]))) {
                filterFlag = true;
            } else {
                if (TOKEN_DOT.equals(String.valueOf(fragmentCharArray[i]))
                        || TOKEN_CLOSE_BRACE.equals(String.valueOf(fragmentCharArray[i]))) {
                    graphQLQuery.append(fragmentCharArray[i]);
                    filterFlag = false;
                } else {
                    if (!filterFlag) {
                        graphQLQuery.append(fragmentCharArray[i]);
                    }
                }
            }
        }
        return graphQLQuery.toString();
    }

}