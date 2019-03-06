# compile = c("Test.ex")
# exekvera = test.funktionens_namn(godtyckligt_nummer)
defmodule Test do
  def double(n) do
    n*2
  end
  def celcius(f) do
    (f-32)/(1.8)
  end
  def area_rectangle(a, b) do
    a*b
  end
  def area_square(a, b) do
    area_rectangle(a, b)
  end
  def area_circle(r) do
    r * r * :math.pi()
  end
  def product_cond(m, n) do
    cond do
      m == 0 ->
        0
      true ->
        n + ((m-1) * n)
    end
  end
  def exp(x, y) do
    cond do
      y == 0 -> 1
      y == 1 -> x

      rem(y, 2) == 0 ->  #if even x^((n/2)^2)
        exp(x, div(y, 2)) * exp(x, div(y, 2))
      rem(y, 2) != 0 ->  #if odd x^(x-1)*x = x^(x^2 - x)
        x * exp(x, (y-1))
    end
  end
  def nth(0, [head | _tail]) do head end
  def nth(n,[_head | tail]) do nth(n-1, tail) end

  def len([]) do 0 end
  def len([_head | tail]) do 1 + len(tail) end

  def sum([]) do 0 end
  def sum([head | tail]) do head + sum(tail) end

  def duplicate([]) do [] end
  def duplicate([head | tail]) do [head, head|duplicate(tail)] end

  def add(x,[]) do [x] end
  def add(x,[x | tail]) do [x | tail] end
  def add(x,[head | tail]) do [head | add(x, tail)] end

  def remove(_,[]) do [] end
  def remove(x,[x | tail]) do remove(x, tail) end
  def remove(x, [head | tail]) do [head | remove(x, tail)] end

  def unique([]) do [] end
  def unique([head | tail]) do [head | unique(remove(head, tail))] end

  def pack([]) do [] end
  def pack([head | tail]) do
    {all, rest} = match(head, tail, [head], [])
    [all | pack(rest)]
  end
  # Returns a list of all instances matching x
 def match(_, [], all, rest) do {all, rest} end
 def match(x, [x | tail], all, rest) do match(x, tail, [x | all], rest) end
 def match(x, [y | tail], all, rest) do match(x, tail, all, [y | rest]) end

 def reverse([]) do [] end
 def reverse([head | tail]) do
    reverse(tail) ++ [head]
 end

 def insert(n, []) do [n] end
 def insert(n, [head | tail]) when n <= head do [n, head | tail] end
 def insert(n, [head | tail]) do [head | insert(n, tail)] end

 def isort(l) do isort(l, []) end
 def isort([], sorted) do sorted end
 def isort([head | tail], sorted) do isort(tail, insert(head, sorted)) end

end
