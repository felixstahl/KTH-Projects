def module Eager do
  def eval_expr({:atm, id}, []) do {:ok, id} end
  def eval_expr({:var, id}, env) do
    case {:var, id} do
      nil -> :error
      {_, str} -> {:ok, id} #vad är str? varför inte returnera id?
    end
  end
  def eval_expr({:cons, {x, y}, {a, b}}, env) do
    case eval_expr({x, y},  env) do
      :error -> :error
      {:ok, y} ->
        case eval_expr({a, b}, env) do
          :error -> :error
          {:ok, ts} -> {y, ts}
        end
      end
    end
#• eval expr({:atm, :a}, []) : returns {:ok, :a}
#• eval expr({:var, :x}, [{:x, :a}]) : returns {:ok, :a}
#• eval expr({:var, :x}, []) : returns :error
#• eval expr({:cons, {:var, :x}, {:atm, :b}}, [{:x, :a}]) : returns {:a, :b}

  def eval_match(:ignore, ..., ...) do
    {:ok, ...}
  end
  def eval_match({:atm, id}, ..., ...) do
    {:ok, ...}
  end
end
# • eval match({:atm, :a}, :a, []) : returns {:ok, []}
# • eval match({:var, :x}, :a, []) : returns {:ok, [{:x, :a}]}
# • eval match({:var, :x}, :a, [{:x, :a}]) : returns {:ok, [{:x,
# :a}]}
# • eval match({:var, :x}, :a, [{:x, :b}]) : returns :fail
# • eval match({:cons, {:var, :x} {:var, :x}}, {:cons, {:atm, :a}
# {:atm, :b}}, []) : returns :fail
