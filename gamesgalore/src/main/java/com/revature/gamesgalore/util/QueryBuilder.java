package com.revature.gamesgalore.util;

public class QueryBuilder {

	
	/**
	 * The final query for each Query Builder instance
	 */
	private StringBuilder query = new StringBuilder();
	
	
	/**
	 * @param table The table to execute select all from
	 * @return
	 */
	public QueryBuilder getSelectAll(String table) {
		query.append("SELECT * FROM ").append(table);
		return this;
	}
	
	/**
	 * @param col The column that will be used in the WHERE clause to filter the data
	 * @param value The value of the column used in the WHERE clause to filter the data
	 * @param quotes This denotes whether the value in the WHERE clause should be surrounded by quotes
	 * @return The current instance of the Query Builder
	 */
	public QueryBuilder addWhereClause(String col, String value, Boolean quotes) {
		if(quotes) {
			query.append(" WHERE ").append(col).append(" = '").append(value + "'");
		}else {
			query.append(" WHERE ").append(col).append(" = ").append(value);
		}
		return this;
	}
	
	/**
	 * @param col The column that will be used in the AND clause to filter the data
	 * @param value The value of the column used in the AND clause to filter the data
	 * @param quotes This denotes whether the value in the AND clause should be surrounded by quotes
	 * @return The current instance of the Query Builder
	 */
	public QueryBuilder addAndClause(String col, String value, Boolean quotes) {
		if(quotes) {
			query.append(" AND ").append(col).append(" = '").append(value + "'");
		}else {
			query.append(" AND ").append(col).append(" = ").append(value);
		}
		return this;
	}
	
	/**
	 * @param join The type of join to be executed in the query
	 * @param table the table that will be joined into the query
	 * @param col1 the column from the left table that will match the right table column
	 * @param col2 the column from the right table that will match the left table column
	 * @return The current instance of the Query Builder
	 */
	public QueryBuilder addJoinClause(String join, String table, String col1, String col2) {
		query.append(join).append(" ").append(table).append(" ON ").append(col1).append(" = ").append(col2);
		return this;
	}
	
	/**
	 * @param limit The amount to LIMIT the data retrieved by
	 * @param offset The amount to OFFSET the retrieve by
	 * @return The current instance of the Query Builder
	 */
	public QueryBuilder addLimitAndOffset(Long limit, Long offset) {
		query.append(" LIMIT ").append(limit).append(" OFFSET ").append(offset);
		return this;
	}
	
	public StringBuilder getQuery() {
		return this.query;
	}
}
