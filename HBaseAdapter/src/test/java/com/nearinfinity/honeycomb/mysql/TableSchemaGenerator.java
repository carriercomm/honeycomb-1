package com.nearinfinity.honeycomb.mysql;

import com.nearinfinity.honeycomb.mysql.gen.ColumnSchema;
import com.nearinfinity.honeycomb.mysql.gen.IndexSchema;
import com.nearinfinity.honeycomb.mysql.gen.TableSchema;
import net.java.quickcheck.Generator;
import net.java.quickcheck.generator.CombinedGenerators;
import net.java.quickcheck.generator.PrimitiveGenerators;
import net.java.quickcheck.generator.distribution.Distribution;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableSchemaGenerator implements Generator<TableSchema> {
    static final int MYSQL_MAX_NAME_LENGTH = 64; // https://dev.mysql.com/doc/refman/5.5/en/identifiers.html
    static final int MYSQL_MAX_COLUMNS = 4096; // https://dev.mysql.com/doc/refman/5.5/en/column-count-limit.html
    private static final Generator<String> nameGen =
            CombinedGenerators.uniqueValues(
                    PrimitiveGenerators.strings(1, MYSQL_MAX_NAME_LENGTH));
    private static final Generator<ColumnSchema> columnGen =
            new ColumnSchemaGenerator();
    private static final Generator<Map<String, ColumnSchema>> columnsGen =
            CombinedGenerators.maps(nameGen, columnGen,
                    PrimitiveGenerators.integers(1, MYSQL_MAX_COLUMNS, Distribution.POSITIV_NORMAL));
    private static final Generator<Integer> numIndicesGen = PrimitiveGenerators.integers(0, 16); // https://dev.mysql.com/doc/refman/5.5/en/column-indexes.html

    @Override
    public TableSchema next() {
        Map<String, ColumnSchema> columnSchemas = columnsGen.next();
        List<String> columns = new ArrayList<String>();
        columns.addAll(columnSchemas.keySet());
        Generator<Map<String, IndexSchema>> indexGen =
                CombinedGenerators.maps(nameGen, new IndexSchemaGenerator(columns),
                        numIndicesGen);

        return new TableSchema(nameGen.next(), columnsGen.next(), indexGen.next());
    }
}