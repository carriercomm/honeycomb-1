#ifndef MACROS_H
#define MACROS_H

#define INFO(format_string) DBUG_PRINT("INFO", format_string)
#define ERROR(format_string) DBUG_PRINT("ERROR", format_string)

#define ARRAY_DELETE(arr) do { if (arr != NULL) { delete[] arr; arr = NULL; } } while(0)
#define MY_FREE(buf) do { if (buf != NULL) { my_free(buf); buf = NULL; } } while(0)

#define HBASECLIENT "com/nearinfinity/honeycomb/hbaseclient/"
#define MYSQLENGINE "com/nearinfinity/honeycomb/mysqlengine/"
#endif
