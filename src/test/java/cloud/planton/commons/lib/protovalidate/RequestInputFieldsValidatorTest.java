package cloud.planton.commons.lib.protovalidate;

import build.buf.gen.cloud.planton.apis.v1.commons.testing.resource.field.input.RegexFieldsProtoValidateTest;
import build.buf.protovalidate.Validator;
import build.buf.protovalidate.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestInputFieldsValidatorTest {

    @Test
    public void testRegexFieldsValidation() throws ValidationException {
        var input1 = RegexFieldsProtoValidateTest.newBuilder()
            .setRegexStringField("abc123")
            .setNoRegexStringField("NoRegex")
            .build();

        Validator validator = new Validator();
        var result1 = validator.validate(input1);
        assertEquals(0, result1.getViolations().size(), () -> "Regex field validation should pass without errors. Expected: 0, Actual: " + result1.getViolations().size());

        // Test failing regex validation
        var input2 = RegexFieldsProtoValidateTest.newBuilder()
            .setRegexStringField("INVALID")
            .build();
        var result2 = validator.validate(input2);
        assertEquals(1, result2.getViolations().size(), () -> "Regex field validation should fail with invalid input. Expected: 1, Actual: " + result2.getViolations().size());
    }
}
