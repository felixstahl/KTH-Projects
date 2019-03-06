defmodule Derivative do
  @type literal() :: {:const, number()} | {:const, atom()}
  @type expr() :: {:add, expr(), expr()} | {:mul, expr(), expr()} | literal()


  def deriv({:const, _}, _) do {:const, 0} end
  def deriv({:var, x}, x) do {:const, 1} end
  def deriv({:var, y}, _) do {:var, y} end
  def deriv({:mul, e1, e2}, x) do {:add, {:mul, deriv(e1, x), e2}, {:mul, e1, deriv(e2, x)}} end
  def deriv({:add, e1, e2}, x) do {:add, deriv(e1, x), deriv(e2, x)} end
  # carry on
  def deriv({:exp, e1, e2}, x) do {:mul, {e2}, {:exp, {:var, x}, {e2 - 1}}} end
  def deriv({:ln, {:var, x}}, x) do {:mul, {:const, 1}, {:exp, {:var, x}, {:const, -1}}} end
  def deriv({:sin, {:var, x}}, x) do {:mul, {:const, -1}, {:cos, {:var, x}}} end

  #simplifying
  def simplify({:const, c}) do {:const, c} end
  def simplify({:var, v}) do {:var, v} end
  def simplify({:exp, e1, e2}) do
    case simplify(e1) do
      {:const, 0} -> {:const, 0}
      {:const, 1} -> simplify(e2)
      s2 -> case simplify(e2) do
        {:const, 0} -> {:const, 0}
        {:const, 1} -> {:const, 1}
        s1 -> {:exp, s1, s2}
      end
    end
  end
  def simplify({:mul, e1, e2) do
    case simplify(e1) do
      {:const, 0} -> {:const, 0}
      {:const, 1} -> simplify(e2)
      s1 -> case simplify(e2) do
        {:const, 0} -> {:const, 0}
        {:const, 1} -> s1
        s2 -> {:mul, s1, s2}
      end
    end
  end
  def simplify({:add, e1, e2}) do
    case simplify(e1) do
      {:const, 0} -> {:const, 0}
      {:const, 1} -> simplify(e2)
      s1 -> case simplify(e2)
  end

end
