public class Logger {

    /**
     * Logs an info message.
     * @param message the message to be logged
     * @param objects an optional array of objects to be used in formatting the input message
     */
    public static void log(String message, Object...objects) {
        // Get the stack trace element of the class/method that initiated the log request.
        StackTraceElement element = new Throwable().getStackTrace()[1];

        // Get the class and method name.
        String className = element.getClassName();
        String methodName = element.getMethodName();

        // If the method is a constructor, replace its name with the class name. If not, add "()" to the end.
        if(methodName.equals("<init>")) {
            methodName = className;
        } else {
            methodName += "()";
        }

        // Get the formatted message from the inputted message and object array.
        String formattedMessage = String.format(message, objects);

        // Print the formatted message with an [INFO] tag.
        System.out.printf("[INFO] %s.%s: %s\n", className, methodName, formattedMessage);
    }

}
