package cadastroaws.demo_aws.service;

import cadastroaws.demo_aws.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;

@Service
public class MemberService {

    @Autowired
    private DynamoDbClient dynamoDbClient;

    private static final String TABLE_NAME = "Members";

    public void saveMember(Member member) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s(member.getId()).build());
        item.put("name", AttributeValue.builder().s(member.getName()).build());
        item.put("email", AttributeValue.builder().s(member.getEmail()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item)
                .build();

        dynamoDbClient.putItem(request);
    }
}